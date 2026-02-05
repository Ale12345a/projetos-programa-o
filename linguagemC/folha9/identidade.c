#include <stdio.h>

#define N 3  // Dimensão da matriz

// Função que verifica se uma matriz é identidade
int identidade(int mat[N][N]) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (i == j) { // diagonal principal
                if (mat[i][j] != 1)
                    return 0; // não é identidade
            } else {      // outras posições
                if (mat[i][j] != 0)
                    return 0; // não é identidade
            }
        }
    }
    return 1; // é identidade
}

// Função para imprimir a matriz (opcional)
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
        {1, 0, 0},
        {0, 1, 0},
        {0, 0, 1}
    };

    imprimir_matriz(matriz);

    if (identidade(matriz)) {
        printf("A matriz é identidade.\n");
    } else {
        printf("A matriz não é identidade.\n");
    }

    return 0;
}
