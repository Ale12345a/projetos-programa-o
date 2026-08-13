#include <stdio.h>
#include <limits.h>  // para usar INT_MIN

int main() {
    int n;
    int max = INT_MIN;  // menor valor possível para começar

    // lê os números até 0
    while (1) {
        scanf("%d", &n);
        if (n == 0) break; // termina ao ler 0
        if (n > max) max = n; // atualiza máximo
    }

    // imprime o máximo encontrado
    printf("%d\n", max);

    return 0;
}
