#include <stdio.h>

int main() {
    int a, b, c;
    int max, min, amplitude;

    // lê os três valores
    printf("Primeiro valor: ");
    scanf("%d", &a);
    printf("Segundo valor: ");
    scanf("%d", &b);
    printf("Terceiro valor: ");
    scanf("%d", &c);

    // calcula o máximo
    max = a;
    if (b > max) max = b;
    if (c > max) max = c;

    // calcula o mínimo
    min = a;
    if (b < min) min = b;
    if (c < min) min = c;

    // calcula a amplitude
    amplitude = max - min;

    // imprime os resultados
    printf("Valor máximo: %d\n", max);
    printf("Valor mínimo: %d\n", min);
    printf("Amplitude: %d\n", amplitude);

    return 0;
}
