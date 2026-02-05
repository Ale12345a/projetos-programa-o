#include <stdio.h>

int magico(int a[20][20], int n) {
    int soma = 0;

    // Soma da primeira linha como referência
    for (int j = 0; j < n; j++) {
        soma += a[0][j];
    }

    // Verificar todas as linhas
    for (int i = 1; i < n; i++) {
        int soma_linha = 0;
        for (int j = 0; j < n; j++) {
            soma_linha += a[i][j];
        }
        if (soma_linha != soma) return 0;
    }

    // Verificar todas as colunas
    for (int j = 0; j < n; j++) {
        int soma_coluna = 0;
        for (int i = 0; i < n; i++) {
            soma_coluna += a[i][j];
        }
        if (soma_coluna != soma) return 0;
    }

    // Verificar diagonal principal
    int soma_diag1 = 0;
    for (int i = 0; i < n; i++) {
        soma_diag1 += a[i][i];
    }
    if (soma_diag1 != soma) return 0;

    // Verificar diagonal secundária
    int soma_diag2 = 0;
    for (int i = 0; i < n; i++) {
        soma_diag2 += a[i][n - 1 - i];
    }
    if (soma_diag2 != soma) return 0;

    // Se passou por todos os testes, é quadrado mágico
    return 1;
}

// Exemplo de teste
int main() {
    int matriz[20][20] = {
        {2, 7, 6},
        {9, 5, 1},
        {4, 3, 8}
    };

    if (magico(matriz, 3)) {
        printf("Eh um quadrado magico!\n");
    } else {
        printf("Nao eh um quadrado magico.\n");
    }

    return 0;
}
