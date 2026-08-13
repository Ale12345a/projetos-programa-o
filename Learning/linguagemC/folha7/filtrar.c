/*Escreva uma função int filtrar_positivos(int vec[], int size) que remove
os valores não positivos (isto é, negativos ou zero) de um vector vec com tamanho size.
A função deve modicar a variável indexada dada de forma a que os valores positivos
quem num segmento inicial do vetor. O resultado deve ser o número de valores positivos
(i.e. o comprimento do segmento nal).*/

#include <stdio.h>

int filtrar_positivos(int vec[], int size) {
    int j = 0; // índice de escrita

    for (int i = 0; i < size; i++) {
        if (vec[i] > 0) {
            vec[j] = vec[i];
            j++;
        }
    }

    return j; // número de elementos positivos
}

int main(void) {
    int numeros[] = {3, -1, 0, 7, -5, 2};
    int tamanho = 6;

    int positivos = filtrar_positivos(numeros, tamanho);

    printf("Número de positivos: %d\n", positivos);
    printf("Vetor filtrado: ");
    for (int i = 0; i < positivos; i++) {
        printf("%d ", numeros[i]);
    }
    printf("\n");

    return 0;
}
