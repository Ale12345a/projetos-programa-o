#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>


int main(int argc, char* argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Uso: %s [-c|-w|-l] ficheiro\n", argv[0]);
        return EXIT_FAILURE;
    }

    char* option = argv[1];
    char* filename = argv[2];

    FILE* file = fopen(filename, "r");
    if (!file) {
        fprintf(stderr, "Erro: não foi possível abrir %s\n", filename);
        return EXIT_FAILURE;
    }

    int c, n_chars = 0, n_words = 0, n_lines = 0;
    int in_word = 0;

    while ((c = fgetc(file)) != EOF) {
        n_chars++;                  // conta caracteres
        if (c == '\n') n_lines++;   // conta linhas

        if (isspace(c)) {
            in_word = 0;            // fim de palavra
        } else if (!in_word) {
            in_word = 1;            // início de nova palavra
            n_words++;
        }
    }

    fclose(file);

    if (strcmp(option, "-c") == 0)
        printf("%d\n", n_chars);
    else if (strcmp(option, "-w") == 0)
        printf("%d\n", n_words);
    else if (strcmp(option, "-l") == 0)
        printf("%d\n", n_lines);
    else {
        fprintf(stderr, "Opção inválida. Use -c, -w ou -l.\n");
        return EXIT_FAILURE;
    }

    return EXIT_SUCCESS;
}
