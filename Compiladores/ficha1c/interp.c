/*
  interp.c - interpretador simples para programas sequenciais com:
   - expressões inteiras: números, variáveis, + - * /, parêntesis
   - atribuições: id = expr;
   - incremento pós-fixo: id++;
   - sequência de comandos separados por ';'
   - sem if/while/functions
*/

#include <errno.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>

/* ---------------- tokenizer ---------------- */

typedef enum {
    TK_EOF,
    TK_NUMBER,
    TK_IDENT,
    TK_PLUS,
    TK_MINUS,
    TK_STAR,
    TK_SLASH,
    TK_ASSIGN,    // =
    TK_SEMI,      // ;
    TK_LPAREN,
    TK_RPAREN,
    TK_INC,       // ++
    TK_UNKNOWN
} TokenKind;

typedef struct {
    TokenKind kind;
    long value;        // for NUMBER
    char *ident;       // for IDENT (dynamically allocated)
} Token;

static const char *src;
static size_t pos;
static Token curtok;

static void token_free(Token *t) {
    if (t->ident) { free(t->ident); t->ident = NULL; }
}

__attribute__((unused))
static void token_set(Token *t, TokenKind k) {
    t->kind = k;
}

static int is_ident_start(char c) { return isalpha((unsigned char)c) || c == '_'; }
static int is_ident_cont(char c)  { return isalnum((unsigned char)c) || c == '_'; }

static void next_token() {
    token_free(&curtok);
    while (isspace((unsigned char)src[pos])) pos++;
    char c = src[pos];
    if (c == '\0') { curtok.kind = TK_EOF; return; }

    if (isdigit((unsigned char)c)) {
        long v = 0;
        while (isdigit((unsigned char)src[pos])) {
            v = v*10 + (src[pos]-'0');
            pos++;
        }
        curtok.kind = TK_NUMBER;
        curtok.value = v;
        return;
    }

    if (is_ident_start(c)) {
        size_t start = pos;
        while (is_ident_cont(src[pos])) pos++;
        size_t len = pos - start;
        curtok.ident = (char*)malloc(len+1);
        memcpy(curtok.ident, src + start, len);
        curtok.ident[len] = '\0';
        curtok.kind = TK_IDENT;
        return;
    }

    // two-char tokens: ++
    if (src[pos] == '+' && src[pos+1] == '+') {
        pos += 2;
        curtok.kind = TK_INC;
        return;
    }

    // single-char tokens
    switch (src[pos++]) {
        case '+': curtok.kind = TK_PLUS; return;
        case '-': curtok.kind = TK_MINUS; return;
        case '*': curtok.kind = TK_STAR; return;
        case '/': curtok.kind = TK_SLASH; return;
        case '=': curtok.kind = TK_ASSIGN; return;
        case ';': curtok.kind = TK_SEMI; return;
        case '(': curtok.kind = TK_LPAREN; return;
        case ')': curtok.kind = TK_RPAREN; return;
        default: curtok.kind = TK_UNKNOWN; return;
    }
}

static void expect(TokenKind k) {
    if (curtok.kind != k) {
        fprintf(stderr, "Syntax error: expected token %d but got %d\n", (int)k, (int)curtok.kind);
        exit(EXIT_FAILURE);
    }
}

/* ---------------- AST ---------------- */

typedef enum { EX_NUM, EX_VAR, EX_BINOP, EX_POSTINC } ExprKind;

typedef struct Expr {
    ExprKind kind;
    long num;           // EX_NUM
    char *name;         // EX_VAR
    char op;            // EX_BINOP: '+', '-', '*', '/'
    struct Expr *left;  // EX_BINOP
    struct Expr *right; // EX_BINOP
} Expr;

/* Constructores */
static Expr* new_num(long v) {
    Expr *e = malloc(sizeof(Expr));
    e->kind = EX_NUM;
    e->num = v;
    e->name = NULL;
    e->left = e->right = NULL;
    return e;
}
static Expr* new_var(const char *s) {
    Expr *e = malloc(sizeof(Expr));
    e->kind = EX_VAR;
    e->name = strdup(s);
    e->left = e->right = NULL;
    return e;
}
static Expr* new_binop(char op, Expr* l, Expr* r) {
    Expr *e = malloc(sizeof(Expr));
    e->kind = EX_BINOP;
    e->op = op;
    e->left = l;
    e->right = r;
    e->name = NULL;
    return e;
}
static Expr* new_postinc(const char *s) {
    Expr *e = malloc(sizeof(Expr));
    e->kind = EX_POSTINC;
    e->name = strdup(s);
    e->left = e->right = NULL;
    return e;
}
static void free_expr(Expr *e) {
    if (!e) return;
    if (e->kind == EX_VAR || e->kind == EX_POSTINC) free(e->name);
    if (e->left) free_expr(e->left);
    if (e->right) free_expr(e->right);
    free(e);
}

/* ---------------- symbol table ---------------- */
typedef struct {
    char *name;
    long value;
} Sym;

static Sym *symtab = NULL;
static size_t symcap = 0, symsz = 0;

static long sym_get(const char *name, int *ok) {
    for (size_t i = 0; i < symsz; ++i) if (strcmp(symtab[i].name, name) == 0) {
        if (ok) *ok = 1;
        return symtab[i].value;
    }
    if (ok) *ok = 0;
    return 0;
}
static void sym_set(const char *name, long val) {
    for (size_t i = 0; i < symsz; ++i) if (strcmp(symtab[i].name, name) == 0) {
        symtab[i].value = val;
        return;
    }
    if (symsz >= symcap) {
        symcap = (symcap ? symcap*2 : 16);
        symtab = realloc(symtab, symcap * sizeof(Sym));
    }
    symtab[symsz].name = strdup(name);
    symtab[symsz].value = val;
    symsz++;
}

/* ---------------- parser ----------------
   program  -> stmtlist EOF
   stmtlist -> stmt (';' stmt)*
   stmt     -> IDENT '=' expr
             | expr         (expr stmt; e.g. a++;)
   expr     -> add
   add      -> mul (('+'|'-') mul)*
   mul      -> primary (('*'|'/') primary)*
   primary  -> NUMBER | IDENT [ '++' ] | '(' expr ')'
*/

// forward declarations
static Expr* parse_expr();
static Expr* parse_expr_with_left(Expr *left);

static Expr* parse_primary() {
    if (curtok.kind == TK_NUMBER) {
        Expr *e = new_num(curtok.value);
        next_token();
        return e;
    }
    if (curtok.kind == TK_IDENT) {
        char *nm = strdup(curtok.ident);
        next_token();
        if (curtok.kind == TK_INC) {
            next_token();
            Expr *e = new_postinc(nm);
            free(nm);
            return e;
        } else {
            Expr *e = new_var(nm);
            free(nm);
            return e;
        }
    }
    if (curtok.kind == TK_LPAREN) {
        next_token();
        Expr *e = parse_expr();
        expect(TK_RPAREN);
        next_token();
        return e;
    }
    fprintf(stderr, "Syntax error: unexpected token in primary\n");
    exit(EXIT_FAILURE);
}

static Expr* parse_mul() {
    Expr *e = parse_primary();
    while (curtok.kind == TK_STAR || curtok.kind == TK_SLASH) {
        char op = (curtok.kind == TK_STAR) ? '*' : '/';
        next_token();
        Expr *r = parse_primary();
        e = new_binop(op, e, r);
    }
    return e;
}

static Expr* parse_add() {
    Expr *e = parse_mul();
    while (curtok.kind == TK_PLUS || curtok.kind == TK_MINUS) {
        char op = (curtok.kind == TK_PLUS) ? '+' : '-';
        next_token();
        Expr *r = parse_mul();
        e = new_binop(op, e, r);
    }
    return e;
}

static Expr* parse_expr() {
    return parse_add();
}

/* ---------------- evaluator ---------------- */
long eval_expr(Expr *e, int *ok) {
    *ok = 1;
    if (!e) { *ok = 0; return 0; }
    switch (e->kind) {
        case EX_NUM: return e->num;
        case EX_VAR: {
            int found;
            long v = sym_get(e->name, &found);
            if (!found) {
                fprintf(stderr, "Runtime error: undefined variable '%s'\n", e->name);
                *ok = 0; return 0;
            }
            return v;
        }
        case EX_BINOP: {
            int ok1, ok2;
            long l = eval_expr(e->left, &ok1);
            long r = eval_expr(e->right, &ok2);
            if (!ok1 || !ok2) { *ok = 0; return 0; }
            switch (e->op) {
                case '+': return l + r;
                case '-': return l - r;
                case '*': return l * r;
                case '/':
                    if (r == 0) {
                        fprintf(stderr, "Runtime error: division by zero\n");
                        *ok = 0; return 0;
                    }
                    return l / r;
                default:
                    fprintf(stderr, "Runtime error: unknown op '%c'\n", e->op);
                    *ok = 0; return 0;
            }
        }
        case EX_POSTINC: {
            int found;
            long v = sym_get(e->name, &found);
            if (!found) {
                fprintf(stderr, "Runtime error: undefined variable '%s'\n", e->name);
                *ok = 0; return 0;
            }
            sym_set(e->name, v+1);
            return v; // post-increment returns old value
        }
        default:
            fprintf(stderr, "Runtime error: eval unknown expr kind\n");
            *ok = 0; return 0;
    }
}

/* -------- continuation parsing (expr with left already parsed) -------- */

static Expr* parse_mul_cont(Expr *left) {
    Expr *e = left;
    while (curtok.kind == TK_STAR || curtok.kind == TK_SLASH) {
        char op = (curtok.kind == TK_STAR) ? '*' : '/';
        next_token();
        Expr *r = parse_primary();
        e = new_binop(op, e, r);
    }
    return e;
}
static Expr* parse_add_cont(Expr *left) {
    Expr *e = parse_mul_cont(left);
    while (curtok.kind == TK_PLUS || curtok.kind == TK_MINUS) {
        char op = (curtok.kind == TK_PLUS) ? '+' : '-';
        next_token();
        Expr *r = parse_mul();
        e = new_binop(op, e, r);
    }
    return e;
}
static Expr* parse_expr_with_left(Expr *left) {
    return parse_add_cont(left);
}

/* ---------------- statement execution ---------------- */

static void execute_statement() {
    if (curtok.kind == TK_IDENT) {
        char *name = strdup(curtok.ident);
        next_token();
        if (curtok.kind == TK_ASSIGN) {
            next_token();
            Expr *e = parse_expr();
            int ok;
            long val = eval_expr(e, &ok);
            if (!ok) exit(EXIT_FAILURE);
            sym_set(name, val);
            free(name);
            free_expr(e);
            return;
        } else {
            Expr *left = new_var(name);
            free(name);
            if (curtok.kind == TK_INC) {
                next_token();
                int ok;
                long v = sym_get(left->name, &ok);
                if (!ok) { fprintf(stderr, "Runtime error: undefined variable '%s'\n", left->name); exit(EXIT_FAILURE); }
                sym_set(left->name, v+1);
                free_expr(left);
                return;
            } else {
                Expr *full = parse_expr_with_left(left);
                int ok;
                (void)eval_expr(full, &ok);
                if (!ok) exit(EXIT_FAILURE);
                free_expr(full);
                return;
            }
        }
    } else {
        Expr *e = parse_expr();
        int ok;
        (void)eval_expr(e, &ok);
        if (!ok) exit(EXIT_FAILURE);
        free_expr(e);
        return;
    }
}

/* ---------------- driver ---------------- */

static void parse_and_execute_all() {
    next_token();
    while (curtok.kind != TK_EOF) {
        execute_statement();
        if (curtok.kind == TK_SEMI) {
            next_token();
            continue;
        } else if (curtok.kind == TK_EOF) break;
        else {
            fprintf(stderr, "Syntax error: missing semicolon\n");
            exit(EXIT_FAILURE);
        }
    }
}

static void print_symtab() {
    for (size_t i = 0; i < symsz; ++i) {
        printf("%s = %ld\n", symtab[i].name, symtab[i].value);
    }
}

int main(int argc, char* argv[]) {
    char buf[1024];
    size_t len = 0;

    printf("Digite o programa:\n");

    while (fgets(buf + len, sizeof(buf) - len, stdin)) {
        len = strlen(buf);
        if (len > 0 && buf[len - 1] == '\n') {
            buf[len - 1] = '\0';
            break;
        }
    }

    src = buf;
    pos = 0;

    parse_and_execute_all();
    print_symtab();
    return 0;
}
