#include "list.h"

void list_remove_first(list* l) {
    if (l->first == NULL) return;
    node* tmp = l->first;
    l->first = l->first->next;
    free(tmp);
    l->size--;
}
