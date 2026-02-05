#include <stdio.h>
#include "list.h"   // ficheiro onde estão declaradas as funções e structs

int main() {
    // Cria uma nova lista vazia
    list *minhaLista = list_new();
    printf("Lista criada.\n");

    // Adiciona elementos no início
    list_add_first(10, minhaLista);
    list_add_first(20, minhaLista);
    list_add_first(30, minhaLista);

    printf("Após adicionar no início: ");
    list_print(minhaLista);

    // Adiciona elementos no fim
    list_add_last(40, minhaLista);
    list_add_last(50, minhaLista);

    printf("Após adicionar no fim: ");
    list_print(minhaLista);

    // Obter primeiro e último elemento
    int first = list_get_first(minhaLista);
    int last = list_get_last(minhaLista);
    printf("Primeiro elemento: %d\n", first);
    printf("Último elemento: %d\n", last);

    // Remover primeiro e último elemento
    list_remove_first(minhaLista);
    list_remove_last(minhaLista);

    printf("Após remover primeiro e último: ");
    list_print(minhaLista);

    // Tamanho da lista
    printf("Tamanho atual da lista: %d\n", list_size(minhaLista));

    // Criar lista aleatória
    list *aleatoria = list_new_random(5, 100);
    printf("Lista aleatória criada: ");
    list_print(aleatoria);

    return 0;
}
