#include "list.h"

node* node_new(int value, node* next) {
    node* n = (node*) malloc(sizeof(node));
    n->val = value;
    n->next = next;
    return n;
}
