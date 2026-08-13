#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* ===== AST ===== */
typedef enum { PLUS, MINUS, TIMES, DIV } BinOp;
typedef enum { IDEXP, NUMEXP, OPEXP } ExpType;

typedef struct _Exp {
    ExpType exp_t;
    union {
        char *ident;  // IDEXP
        int num;      // NUMEXP
        struct {
            struct _Exp *left;
            BinOp op;
            struct _Exp *right;
        } opexp; // OPEXP
    } fields;
} Exp;

typedef struct _Exp* ExpPtr;

typedef enum { ASSIGNSTM, INCRSTM, COMPOUNDSTM } StmType;

typedef struct _Stm {
    StmType stm_t;
    union {
        struct {
            char *ident;
            ExpPtr exp;
        } assign;
        char *incr;
        struct {
            struct _Stm *fst;
            struct _Stm *snd;
        } compound;
    } fields;
} Stm;

typedef struct _Stm* StmPtr;

/* ===== Tabela de variáveis ===== */
typedef struct _Entry {
    char *ident;
    int value;
    struct _Entry *next;
} Entry;

typedef Entry* Table;

int* lookup(Table tbl, char *id) {
    for (Entry *e = tbl; e != NULL; e = e->next) {
        if (strcmp(e->ident, id) == 0) return &e->value;
    }
    return NULL;
}

Table update(Table tbl, char *id, int val) {
    for (Entry *e = tbl; e != NULL; e = e->next) {
        if (strcmp(e->ident, id) == 0) {
            e->value = val;
            return tbl;
        }
    }
    Entry *newE = malloc(sizeof(Entry));
    newE->ident = strdup(id);
    newE->value = val;
    newE->next = tbl;
    return newE;
}

/* ===== Interpretador ===== */
int interpret_exp(ExpPtr exp, Table tbl) {
    switch (exp->exp_t) {
        case NUMEXP:
            return exp->fields.num;
        case IDEXP: {
            int *val = lookup(tbl, exp->fields.ident);
            if (!val) {
                fprintf(stderr, "Erro: variável %s não inicializada\n", exp->fields.ident);
                exit(1);
            }
            return *val;
        }
        case OPEXP: {
            int l = interpret_exp(exp->fields.opexp.left, tbl);
            int r = interpret_exp(exp->fields.opexp.right, tbl);
            switch (exp->fields.opexp.op) {
                case PLUS:  return l + r;
                case MINUS: return l - r;
                case TIMES: return l * r;
                case DIV:
                    if (r == 0) { fprintf(stderr, "Divisão por zero!\n"); exit(1); }
                    return l / r;
            }
        }
    }
    return 0;
}

Table interpret_stm(StmPtr stm, Table tbl) {
    switch (stm->stm_t) {
        case ASSIGNSTM: {
            int val = interpret_exp(stm->fields.assign.exp, tbl);
            return update(tbl, stm->fields.assign.ident, val);
        }
        case INCRSTM: {
            int *old = lookup(tbl, stm->fields.incr);
            if (!old) {
                fprintf(stderr, "Erro: variável %s não inicializada\n", stm->fields.incr);
                exit(1);
            }
            return update(tbl, stm->fields.incr, *old + 1);
        }
        case COMPOUNDSTM: {
            Table t1 = interpret_stm(stm->fields.compound.fst, tbl);
            return interpret_stm(stm->fields.compound.snd, t1);
        }
    }
    return tbl;
}

/* ===== Construtores auxiliares ===== */
ExpPtr mk_numexp(int n) {
    ExpPtr e = malloc(sizeof(Exp));
    e->exp_t = NUMEXP;
    e->fields.num = n;
    return e;
}

ExpPtr mk_idexp(char *id) {
    ExpPtr e = malloc(sizeof(Exp));
    e->exp_t = IDEXP;
    e->fields.ident = strdup(id);
    return e;
}

ExpPtr mk_opexp(ExpPtr l, BinOp op, ExpPtr r) {
    ExpPtr e = malloc(sizeof(Exp));
    e->exp_t = OPEXP;
    e->fields.opexp.left = l;
    e->fields.opexp.op = op;
    e->fields.opexp.right = r;
    return e;
}

StmPtr mk_assign(char *id, ExpPtr e) {
    StmPtr s = malloc(sizeof(Stm));
    s->stm_t = ASSIGNSTM;
    s->fields.assign.ident = strdup(id);
    s->fields.assign.exp = e;
    return s;
}

StmPtr mk_incr(char *id) {
    StmPtr s = malloc(sizeof(Stm));
    s->stm_t = INCRSTM;
    s->fields.incr = strdup(id);
    return s;
}

StmPtr mk_compound(StmPtr s1, StmPtr s2) {
    StmPtr s = malloc(sizeof(Stm));
    s->stm_t = COMPOUNDSTM;
    s->fields.compound.fst = s1;
    s->fields.compound.snd = s2;
    return s;
}

/* ===== MAIN com testes ===== */
int main() {
    // Programa exemplo 1: a = 3; b = 2; a = a*b;
    StmPtr prog1 =
        mk_compound(
            mk_assign("a", mk_numexp(3)),
            mk_compound(
                mk_assign("b", mk_numexp(2)),
                mk_assign("a", mk_opexp(mk_idexp("a"), TIMES, mk_idexp("b")))
            )
        );

    Table t = NULL;
    t = interpret_stm(prog1, t);

    int *va = lookup(t, "a");
    int *vb = lookup(t, "b");
    printf("Resultado prog1: a=%d, b=%d\n", va ? *va : -1, vb ? *vb : -1);

    // Programa exemplo 2: a = 1; a++; b = a*2;
    StmPtr prog2 =
        mk_compound(
            mk_assign("a", mk_numexp(1)),
            mk_compound(
                mk_incr("a"),
                mk_assign("b", mk_opexp(mk_idexp("a"), TIMES, mk_numexp(2)))
            )
        );

    Table t2 = NULL;
    t2 = interpret_stm(prog2, t2);

    va = lookup(t2, "a");
    vb = lookup(t2, "b");
    printf("Resultado prog2: a=%d, b=%d\n", va ? *va : -1, vb ? *vb : -1);

    return 0;
}
