#include "list.h"

void list_add_first(int value, list* l) {
    node* n = node_new(value, l->first);
    l->first = n;
    l->size++;
}
