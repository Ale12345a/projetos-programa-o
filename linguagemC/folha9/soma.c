#include <stdio.h>

#define N 3  // Dimensão da matriz

// Soma da diagonal principal (i == j)
int soma_diagonal1(int mat[N][N]) {
    int soma = 0;
    for (int i = 0; i < N; i++) {
        soma += mat[i][i];
    }
    return soma;
}

// Soma da diagonal secundária (i + j == N - 1)
int soma_diagonal2(int mat[N][N]) {
    int soma = 0;
    for (int i = 0; i < N; i++) {
        soma += mat[i][N - 1 - i];
    }
    return soma;
}

// Função opcional para imprimir a matriz
void imprimir_matriz(int mat[N][N]) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            printf("%d ", mat[i][j]);
        }
        printf("\n");
    }
}

int main() {
    int matriz[N][N] = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    imprimir_matriz(matriz);

    printf("Soma da diagonal principal: %d\n", soma_diagonal1(matriz));
    printf("Soma da diagonal secundaria: %d\n", soma_diagonal2(matriz));

    return 0;
}
