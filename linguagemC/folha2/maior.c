#include <stdio.h>

int main(void) {
    int a, b, c, maior;

    // lê 3 valores
    scanf("%d %d %d", &a, &b, &c);

    // determina o maior
    maior = a;         // supõe que o primeiro é o maior
    if (b > maior) {
        maior = b;
    }
    if (c > maior) {
        maior = c;
    }

    // imprime o maior valor
    printf("%d\n", maior);

    return 0;
}
