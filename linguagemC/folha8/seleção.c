#include <stdio.h>

#define MAX 1000

void selecao(int vec[], int size) {
    for (int i = 0; i < size - 1; i++) {
        int min_idx = i;
        for (int j = i + 1; j < size; j++) {
            if (vec[j] < vec[min_idx]) {
                min_idx = j;
            }
        }
        // trocar vec[i] com vec[min_idx]
        int temp = vec[i];
        vec[i] = vec[min_idx];
        vec[min_idx] = temp;
    }
}

int main(void) {
    int vec[MAX];
    int size = 0;
    int num;

    // leitura dos inteiros positivos até zero
    while (size < MAX && scanf("%d", &num) == 1 && num != 0) {
        vec[size++] = num;
    }

    // ordenação por seleção
    selecao(vec, size);

    // imprimir vetor ordenado
    for (int i = 0; i < size; i++) {
        printf("%d ", vec[i]);
    }
    printf("\n");

    return 0;
}
