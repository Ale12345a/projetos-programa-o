#include <stdio.h>

// Função que calcula o máximo divisor comum usando o método de Euclides
int mdc(int a, int b) {
    int r;
    while (b != 0) {
        r = a % b;
        a = b;
        b = r;
    }
    return a;
}

int main() {
    int numerador, denominador;
    int divisor;

    // Lê numerador e denominador
    printf("Numerador: ");
    scanf("%d", &numerador);
    printf("Denominador: ");
    scanf("%d", &denominador);

    // Calcula o m.d.c.
    divisor = mdc(numerador, denominador);

    // Imprime a fração simplificada
    printf("A fração %d/%d é equivalente a %d/%d\n",
           numerador, denominador,
           numerador / divisor, denominador / divisor);

    return 0;
}
