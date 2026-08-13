#include <stdio.h>

int main() {
    int a, b, c;

    // lê os lados do triângulo
    printf("Primeiro lado: ");
    scanf("%d", &a);
    printf("Segundo lado: ");
    scanf("%d", &b);
    printf("Terceiro lado: ");
    scanf("%d", &c);

    // classifica o triângulo
    if (a == b && b == c) {
        printf("Triângulo equilátero.\n");
    } else if (a == b || a == c || b == c) {
        printf("Triângulo isósceles.\n");
    } else {
        printf("Triângulo escaleno.\n");
    }

    return 0;
}

