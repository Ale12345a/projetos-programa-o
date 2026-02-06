/*Um quadrado mágico é uma matriz quadrada de números inteiros tal que todas as
linhas, colunas e diagonais somam o mesmo valor. No exemplo seguinte cada linha, coluna
e diagonal soma o mesmo valor (15 neste caso):

 2 7 6
9 5 1
4 3 8


Escreva uma função int magico(int a[20][20], int n) que testa se uma matriz é um
quadrado mágico. A matriz é representada por uma variável indexada a com dimensão
declarada 20 × 20; o argumento n indica qual sub-matriz a considerar: por exemplo, se n =
3 devemos testar se a sub-matriz de 3 × 3 é quadrado mágico (e ignorar o resto da matriz).
O resultado deve ser um inteiro: 1 se é quadrado mágico e 0 caso contrário.*/

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
