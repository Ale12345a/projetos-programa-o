#include "list.h"

void list_print(list* l) {
    node* cur = l->first;
    printf("[");
    while (cur != NULL) {
        printf("%d", cur->val);
        if (cur->next != NULL) printf(", ");
        cur = cur->next;
    }
    printf("]\n");
}
