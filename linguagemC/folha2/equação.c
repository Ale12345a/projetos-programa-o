#include <stdio.h>

int main() {
    double x, resultado;

    // lê o valor de x
    scanf("%lf", &x);

    // calcula a expressão usando multiplicações repetidas
    resultado = 3*x*x*x*x*x    // 3x^5
              + 2*x*x*x*x      // 2x^4
              - 5*x*x*x        // -5x^3
              - x*x            // -x^2
              + 7*x            // +7x
              - 6;             // -6

    // imprime o resultado
    printf("%lf\n", resultado);

    return 0;
}
