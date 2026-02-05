#include "list.h"

#include <time.h>

list* list_new_random(int n, int max) {
    list* l = list_new();
    srand(time(NULL));
    for (int i = 0; i < n; i++) {
        int value = rand() % (max + 1);
        list_add_last(value, l);
    }
    return l;
}
