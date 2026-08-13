/*Escreva um programa que lê repetidamente carateres até encontrar uma mudança de
linha (\n) e contabiliza o número total de letras (isto é, carateres de 'A' a 'Z' e de 'a' a
'z'). Exemplo (em sublinhado o texto introduzido pelo utilizador):
Ola, Mundo!
A frase contém 8 letra(s)
Sugestão: usar getchar() para ler um carater de cada vez.*/

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
