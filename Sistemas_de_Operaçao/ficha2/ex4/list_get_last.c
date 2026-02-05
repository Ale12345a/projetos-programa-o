#include "list.h"

int list_get_last(list* l) {
    if (l->first == NULL) {
        fprintf(stderr, "Erro: lista vazia\n");
        exit(1);
    }
    node* cur = l->first;
    while (cur->next != NULL)
        cur = cur->next;
    return cur->val;
}
