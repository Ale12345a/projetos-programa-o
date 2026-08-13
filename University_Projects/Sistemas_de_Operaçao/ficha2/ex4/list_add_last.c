#include "list.h"

void list_add_last(int value, list* l) {
    node* n = node_new(value, NULL);
    if (l->first == NULL) { // lista vazia
        l->first = n;
    } else {
        node* cur = l->first;
        while (cur->next != NULL)
            cur = cur->next;
        cur->next = n;
    }
    l->size++;
}
