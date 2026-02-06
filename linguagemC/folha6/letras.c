/*Escreva um programa que lê carateres e contabiliza o número de vezes de ocorreu cada
letra (A a Z) ignorando a distinção entre maiúsculas e minúsculas. No nal deve imprimir
uma tabela com as contagens:
A: 3 B: 2 C: 2 D: 1 E: 7 ...
Sugestão: utilize uma variável indexada com 26 elementos para contabilizar a contagem das
letras. */

#include <stdio.h>
#include <ctype.h>  // para toupper()

int main(void) {
    int contagem[26] = {0};  // contador de A a Z
    int c;

    printf("Introduza texto (Ctrl+D para terminar no Linux/Mac ou Ctrl+Z no Windows):\n");

    while ((c = getchar()) != EOF) {
        c = toupper(c);
        if (c >= 'A' && c <= 'Z') {
            contagem[c - 'A']++;
        }
    }

    printf("\nContagem de letras:\n");
    for (int i = 0; i < 26; i++) {
        printf("%c: %d  ", 'A' + i, contagem[i]);
        if ((i + 1) % 5 == 0)  // quebra de linha a cada 5 letras
            printf("\n");
    }
    printf("\n");

    return 0;
}
