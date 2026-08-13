#include <stdio.h>

int main() {
    int n;
    int i;

    printf("Número inteiro: ");
    scanf("%d", &n);

    if (n <= 0) {
        printf("O número deve ser maior que 0.\n");
        return 1;
    }

    printf("%d :", n);

    i = 2;  // começamos pelo menor primo
    while (n > 1) {
        while (n % i == 0) {  // enquanto i divide n
            printf(" %d", i); // imprime o fator
            n = n / i;        // divide n por i
        }
        i++;  // passa para o próximo número
    }

    printf("\n");
    return 0;
}
