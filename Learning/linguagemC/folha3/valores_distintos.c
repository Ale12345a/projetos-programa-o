#include <stdio.h>

int main() {
    int a, b, c;
    int distintos;

    // lê os três valores
    printf("Primeiro valor: ");
    scanf("%d", &a);
    printf("Segundo valor: ");
    scanf("%d", &b);
    printf("Terceiro valor: ");
    scanf("%d", &c);

    // conta quantos valores distintos existem
    if (a == b && b == c) {
        distintos = 1;
    } else if (a != b && a != c && b != c) {
        distintos = 3;
    } else {
        distintos = 2;
    }

    // imprime o resultado
    printf("Resposta: %d valores distintos.\n", distintos);

    return 0;
}
