#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* ---- Tipos para Expressões ---- */
typedef enum { PLUS, MINUS, TIMES, DIV } BinOp;
typedef enum { IDEXP, NUMEXP, OPEXP } ExpType;

typedef struct _Exp {
    ExpType exp_t;
    union {
        char *ident;    // IDEXP
        int num;        // NUMEXP
        struct {
            struct _Exp *left;
            BinOp op;
            struct _Exp *right;
        } opexp;        // OPEXP
    } fields;
} *Exp;

/* ---- Tipos para Comandos ---- */
typedef enum { ASSIGNSTM, INCRSTM, COMPOUNDSTM } StmType;

typedef struct _Stm {
    StmType stm_t;
    union {
        struct { char *ident; Exp exp; } assign; // a = expr
        char *incr;                              // a++
        struct { struct _Stm *fst; struct _Stm *snd; } compound; // seq
    } fields;
} *Stm;

/* ---- Construtores ---- */
Exp mk_idexp(char *s) {
    Exp e = malloc(sizeof(*e));
    e->exp_t = IDEXP;
    e->fields.ident = strdup(s);
    return e;
}
Exp mk_numexp(int n) {
    Exp e = malloc(sizeof(*e));
    e->exp_t = NUMEXP;
    e->fields.num = n;
    return e;
}
Exp mk_opexp(Exp l, BinOp op, Exp r) {
    Exp e = malloc(sizeof(*e));
    e->exp_t = OPEXP;
    e->fields.opexp.left = l;
    e->fields.opexp.op = op;
    e->fields.opexp.right = r;
    return e;
}

Stm mk_assign(char *s, Exp e) {
    Stm stm = malloc(sizeof(*stm));
    stm->stm_t = ASSIGNSTM;
    stm->fields.assign.ident = strdup(s);
    stm->fields.assign.exp = e;
    return stm;
}
Stm mk_incr(char *s) {
    Stm stm = malloc(sizeof(*stm));
    stm->stm_t = INCRSTM;
    stm->fields.incr = strdup(s);
    return stm;
}
Stm mk_compound(Stm s1, Stm s2) {
    Stm stm = malloc(sizeof(*stm));
    stm->stm_t = COMPOUNDSTM;
    stm->fields.compound.fst = s1;
    stm->fields.compound.snd = s2;
    return stm;
}

/* ---- Funções pedidas ---- */
int ids_in_exp(Exp exp, char *ident) {
    if (!exp) return 0;
    switch (exp->exp_t) {
        case IDEXP:
            return strcmp(exp->fields.ident, ident) == 0;
        case NUMEXP:
            return 0;
        case OPEXP:
            return ids_in_exp(exp->fields.opexp.left, ident) ||
                   ids_in_exp(exp->fields.opexp.right, ident);
    }
    return 0;
}

int ids_in_stm(Stm stm, char *ident) {
    if (!stm) return 0;
    switch (stm->stm_t) {
        case ASSIGNSTM:
            return (strcmp(stm->fields.assign.ident, ident) == 0) ||
                   ids_in_exp(stm->fields.assign.exp, ident);
        case INCRSTM:
            return strcmp(stm->fields.incr, ident) == 0;
        case COMPOUNDSTM:
            return ids_in_stm(stm->fields.compound.fst, ident) ||
                   ids_in_stm(stm->fields.compound.snd, ident);
    }
    return 0;
}

/* ---- Programa de teste ---- */
int main() {
    // exemplo: a = 3; b = a+2; c++;
    Stm prog = mk_compound(
        mk_assign("a", mk_numexp(3)),
        mk_compound(
            mk_assign("b", mk_opexp(mk_idexp("a"), PLUS, mk_numexp(2))),
            mk_incr("c")
        )
    );

    printf("Identificador a ocorre? %d\n", ids_in_stm(prog, "a"));
    printf("Identificador b ocorre? %d\n", ids_in_stm(prog, "b"));
    printf("Identificador c ocorre? %d\n", ids_in_stm(prog, "c"));
    printf("Identificador x ocorre? %d\n", ids_in_stm(prog, "x"));

    return 0;
}
