#ifndef LIST_H
#define LIST_H

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/* definição de node */
typedef struct anode {
    int val;
    struct anode* next;
} node;

/* definição de list */
typedef struct {
    int size;
    node* first;
} list;

/* protótipos das funções */
node* node_new(int value, node* next);
list* list_new();
list* list_new_random(int n, int max);
void list_add_first(int value, list* l);
void list_add_last(int value, list* l);
int list_get_first(list* l);
int list_get_last(list* l);
void list_remove_first(list* l);
void list_remove_last(list* l);
int list_size(list* l);
void list_print(list* l);

#endif
