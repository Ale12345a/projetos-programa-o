#include <stdio.h>

// Função auxiliar para calcular o MDC usando o algoritmo de Euclides
int mdc(int a, int b) {
    while (b != 0) {
        int r = a % b;
        a = b;
        b = r;
    }
    return a;
}

// Função que reduz uma fração à forma simplificada
void reduzir(int *pnum, int *pdenom) {
    int d = mdc(*pnum, *pdenom);
    *pnum /= d;
    *pdenom /= d;
}

// Exemplo de uso
int main() {
    int num = 56, denom = 32;
    reduzir(&num, &denom);
    printf("Fração simplificada: %d/%d\n", num, denom);
    return 0;
}
