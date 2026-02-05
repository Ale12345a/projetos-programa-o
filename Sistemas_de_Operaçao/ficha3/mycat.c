#include <stdio.h>
#include <stdlib.h>

#define BUFFER_SIZE 1024

int main(int argc, char* argv[]) {
    if (argc < 2) {
        fprintf(stderr, "Uso: %s ficheiro1 [ficheiro2 ...]\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    // Percorre todos os ficheiros dados na linha de comando
    for (int i = 1; i < argc; i++) {
        FILE* file = fopen(argv[i], "r");
        if (file == NULL) {
            fprintf(stderr, "error: could not open %s\n", argv[i]);
            continue;  // tenta abrir os restantes
        }

        char buffer[BUFFER_SIZE];
        int nchars = fread(buffer, sizeof(char), BUFFER_SIZE, file);

        while (nchars > 0) {
            fwrite(buffer, sizeof(char), nchars, stdout);
            nchars = fread(buffer, sizeof(char), BUFFER_SIZE, file);
        }

        fclose(file);
    }

    exit(EXIT_SUCCESS);
}
