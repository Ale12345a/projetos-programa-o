#include "list.h"

int list_get_first(list* l) {
    if (l->first == NULL) {
        fprintf(stderr, "Erro: lista vazia\n");
        exit(1);
    }
    return l->first->val;
}
