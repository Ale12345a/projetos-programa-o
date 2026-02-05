#include <stdio.h>

// função que calcula a soma dos divisores de n (excluindo n)
int soma_divisores(int n) {
    int soma = 0;

    for (int i = 1; i < n; i++) {
        if (n % i == 0) {  // se i divide n
            soma += i;      // adiciona à soma
        }
    }

    return soma;
}

// exemplo de uso
int main() {
    int n;

    printf("Digite um número: ");
    scanf("%d", &n);

    printf("Soma dos divisores de %d: %d\n", n, soma_divisores(n));

    return 0;
}
