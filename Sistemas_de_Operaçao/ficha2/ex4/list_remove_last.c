#include "list.h"

void list_remove_last(list* l) {
    if (l->first == NULL) return;

    if (l->first->next == NULL) { // só 1 elemento
        free(l->first);
        l->first = NULL;
    } else {
        node* cur = l->first;
        while (cur->next->next != NULL)
            cur = cur->next;
        free(cur->next);
        cur->next = NULL;
    }
    l->size--;
}
