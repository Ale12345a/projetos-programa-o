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
