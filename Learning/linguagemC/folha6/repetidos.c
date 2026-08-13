/*Escreva um programa que lê uma sequência de valores inteiros positivos terminada por
-1 e no nal escreve toda a sequência mas sem repetidos.
Sugestão: guarde os valores numa variável indexada à medida que são lidos; de cada vez que
lê um novo valor pode vericar se já é repetido (e nesse caso não o guarde novamente). No
nal imprima todos os valores guardados. */

#include <stdio.h>

int repetidos(int vec[], unsigned size) {
    for (unsigned i = 0; i < size; i++) {
        for (unsigned j = i + 1; j < size; j++) {
            if (vec[i] == vec[j]) {
                return 1;  // encontrou repetido
            }
        }
    }
    return 0;  // nenhum repetido
}

int main(void) {
    int a[5] = {2, -1, 0, 2, -1};
    int b[5] = {3, 4, 1, 2, -1};

    printf("%d\n", repetidos(a, 5)); // imprime 1
    printf("%d\n", repetidos(b, 5)); // imprime 0

    return 0;
}
