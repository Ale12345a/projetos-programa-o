#include <stdio.h>

#define MAX 1000

void insercao(int vec[], int size) {
    for (int i = 1; i < size; i++) {
        int chave = vec[i];
        int j = i - 1;
        while (j >= 0 && vec[j] > chave) {
            vec[j + 1] = vec[j];
            j--;
        }
        vec[j + 1] = chave;
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

    // ordenação por inserção
    insercao(vec, size);

    // imprimir vetor ordenado
    for (int i = 0; i < size; i++) {
        printf("%d ", vec[i]);
    }
    printf("\n");

    return 0;
}
