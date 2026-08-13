/*Escreva uma função int contar_maiores(int vec[], int size, int val) cujos
argumento são uma variável indexada vec com tamanho size e um valor val e cujo resultado
deve ser a contagem do número de elementos de vec que são estritamente maiores do que
val.*/

#include <stdio.h>

int contar_maiores(int vec[], int size, int val) {
    int contador = 0;

    for (int i = 0; i < size; i++) {
        if (vec[i] > val) {
            contador++;
        }
    }

    return contador;
}

int main(void) {
    int numeros[] = {3, 7, 1, 9, 4};
    int val = 5;

    printf("%d elementos são maiores que %d\n", contar_maiores(numeros, 5, val), val);

    return 0;
}
