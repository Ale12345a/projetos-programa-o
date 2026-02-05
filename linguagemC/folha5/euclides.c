#include <stdio.h>

int main() {
    int a, b;
    int iteracoes = 1;

    printf("Introduza dois inteiros: ");
    scanf("%d %d", &a, &b);

    printf("mdc(%d,%d)", a, b);

    while (a != b) {
        if (a > b) {
            a = a - b;
        } else {
            b = b - a;
        }
        iteracoes++;
        printf(" = mdc(%d,%d)", a, b);
    }

    printf(" = %d\n", a);
    printf("%d iteracoes\n", iteracoes);

    return 0;
}
