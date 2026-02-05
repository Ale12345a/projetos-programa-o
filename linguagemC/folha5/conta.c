#include <stdio.h>

int main() {
    char c;
    int contador = 0;

    printf("Introduza uma frase:\n");

    while ((c = getchar()) != '\n') {
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
            contador++;
        }
    }

    printf("A frase contem %d letra(s)\n", contador);

    return 0;
}
