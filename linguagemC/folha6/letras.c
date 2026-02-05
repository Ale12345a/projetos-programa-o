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
