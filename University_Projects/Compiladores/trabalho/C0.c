#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// =================================
// AST e Tipos
// =================================
typedef enum { PLUS, MINUS, TIMES, DIV } BinOp;

typedef enum { IDEXP, NUMEXP, OPEXP } ExpType;
typedef struct _Exp {
    ExpType exp_t;
    union {
        char *ident;          // IDEXP
        int num;              // NUMEXP
        struct {
            struct _Exp *left;
            BinOp op;
            struct _Exp *right;
        } opexp;              // OPEXP
    } fields;
} Exp;

typedef enum { ASSIGNSTM, COMPOUNDSTM, IFSTM, WHILESTM, RETURNSTM } StmType;
typedef struct _Stm {
    StmType stm_t;
    union {
        struct { char *ident; Exp *exp; } assign;
        struct { struct _Stm **stmts; int count; } compound;
        struct { Exp *cond; struct _Stm *thenStm; struct _Stm *elseStm; } ifstm;
        struct { Exp *cond; struct _Stm *body; } whilestm;
        struct { Exp *exp; } returnstm;
    } fields;
} Stm;

// =================================
// Tabela de Variáveis
// =================================
typedef struct {
    char *name;
    int value;
} Var;

typedef struct {
    Var *vars;
    int size;
} Table;

int lookup(Table *tbl, const char *name) {
    for (int i = 0; i < tbl->size; i++)
        if (strcmp(tbl->vars[i].name, name) == 0) return tbl->vars[i].value;
    fprintf(stderr, "Variável não definida: %s\n", name);
    exit(1);
}

void update(Table *tbl, const char *name, int value) {
    for (int i = 0; i < tbl->size; i++)
        if (strcmp(tbl->vars[i].name, name) == 0) { tbl->vars[i].value = value; return; }
    tbl->size++;
    tbl->vars = realloc(tbl->vars, tbl->size * sizeof(Var));
    tbl->vars[tbl->size-1].name = strdup(name);
    tbl->vars[tbl->size-1].value = value;
}

// =================================
// Interpretador
// =================================
int interpExp(Exp *e, Table *tbl) {
    switch(e->exp_t) {
        case NUMEXP: return e->fields.num;
        case IDEXP: return lookup(tbl, e->fields.ident);
        case OPEXP: {
            int l = interpExp(e->fields.opexp.left, tbl);
            int r = interpExp(e->fields.opexp.right, tbl);
            switch(e->fields.opexp.op) {
                case PLUS: return l + r;
                case MINUS: return l - r;
                case TIMES: return l * r;
                case DIV: return l / r;
            }
        }
    }
    return 0;
}

void interpStm(Stm *s, Table *tbl) {
    switch(s->stm_t) {
        case ASSIGNSTM:
            update(tbl, s->fields.assign.ident, interpExp(s->fields.assign.exp, tbl));
            break;
        case COMPOUNDSTM:
            for (int i = 0; i < s->fields.compound.count; i++)
                interpStm(s->fields.compound.stmts[i], tbl);
            break;
        case IFSTM:
            if (interpExp(s->fields.ifstm.cond, tbl))
                interpStm(s->fields.ifstm.thenStm, tbl);
            else if (s->fields.ifstm.elseStm)
                interpStm(s->fields.ifstm.elseStm, tbl);
            break;
        case WHILESTM:
            while (interpExp(s->fields.whilestm.cond, tbl))
                interpStm(s->fields.whilestm.body, tbl);
            break;
        case RETURNSTM:
            // Ignoramos return para este exemplo simples
            break;
    }
}

// =================================
// Exemplo factorial
// =================================
int main() {
    Table tbl = {NULL,0};

    // a = 5; b = a * 2;
    Exp e1 = {NUMEXP, .fields.num=5};
    Exp e2 = {OPEXP, .fields.opexp={&e1, TIMES, &((Exp){NUMEXP, .fields.num=2})}};
    Stm *stmts[2];
    stmts[0] = &(Stm){ASSIGNSTM, .fields.assign={"a",&e1}};
    stmts[1] = &(Stm){ASSIGNSTM, .fields.assign={"b",&e2}};
    Stm program = {COMPOUNDSTM, .fields.compound={stmts,2}};

    interpStm(&program, &tbl);

    printf("a=%d, b=%d\n", lookup(&tbl,"a"), lookup(&tbl,"b"));
}
