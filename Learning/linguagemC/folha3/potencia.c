#include <stdio.h>

// função para calcular x^n
int potencia(int x, int n) {
    int resultado = 1; // x^0 = 1

    for (int i = 0; i < n; i++) {
        resultado *= x; // multiplica x n vezes
    }

    return resultado;
}

// exemplo de uso
int main() {
    int base, expoente;

    printf("Base: ");
    scanf("%d", &base);
    printf("Expoente: ");
    scanf("%d", &expoente);

    printf("%d^%d = %d\n", base, expoente, potencia(base, expoente));

    return 0;
}
