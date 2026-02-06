/*Escreva uma função
void max_min(int vec[], int size, int *pmax, int *pmin);
que determina o valor máximo e mínimo de um vector; os resultados devem ser atribuidos
ao conteúdo dos apontadores pmax e pmin. Pode assumir que size é sempre maior que zero.*/

#include <stdio.h>

void max_min(int vec[], int size, int *pmax, int *pmin) {
    // inicializa máximo e mínimo com o primeiro elemento
    *pmax = vec[0];
    *pmin = vec[0];

    for (int i = 1; i < size; i++) {
        if (vec[i] > *pmax) {
            *pmax = vec[i];
        }
        if (vec[i] < *pmin) {
            *pmin = vec[i];
        }
    }
}

// Exemplo de uso
int main() {
    int v[] = {7, 3, 9, 1, 5};
    int max, min;

    max_min(v, 5, &max, &min);

    printf("Max: %d, Min: %d\n", max, min);

    return 0;
}
