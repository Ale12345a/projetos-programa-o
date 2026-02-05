#include <stdio.h>

int main() {
    int c, prev;
    int palavras = 0;

    prev = ' ';  // começamos como se já tivéssemos um separador

    while ((c = getchar()) != EOF) {
        // se o anterior é "normal" e o atual é "branco", fechamos uma palavra
        if ((prev != ' ' && prev != '\t' && prev != '\n') &&
            (c == ' ' || c == '\t' || c == '\n')) {
            palavras++;
        }
        prev = c;
    }

    // caso o texto não termine em branco, ainda temos uma palavra aberta
    if (prev != ' ' && prev != '\t' && prev != '\n') {
        palavras++;
    }
    printf("Total de palavras: %d\n", palavras);

    return 0;
}
