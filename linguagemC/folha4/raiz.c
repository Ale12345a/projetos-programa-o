#include <stdio.h>

int main() {
    double a;
    double x;  // aproximação atual

    printf("Digite um valor a > 0: ");
    scanf("%lf", &a);

    if (a <= 0) {
        printf("O valor deve ser maior que 0.\n");
        return 1;
    }

    x = a / 2.0; // primeira aproximação

    printf("Aproximações sucessivas da raiz quadrada de %.2lf:\n", a);

    for (int i = 1; i <= 10; i++) {
        x = 0.5 * (x + a / x);  // fórmula do método Babilónico
        printf("x%d = %.10lf\n", i, x);
    }

    return 0;
}
