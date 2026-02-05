#include <stdio.h>
#include <stdlib.h>

#define BUFFER_SIZE 1024

int main(int argc, char* argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Uso: %s ficheiro_origem ficheiro_destino\n", argv[0]);
        return EXIT_FAILURE;
    }

    FILE* src = fopen(argv[1], "rb");
    if (!src) {
        fprintf(stderr, "Erro: não foi possível abrir %s para leitura\n", argv[1]);
        return EXIT_FAILURE;
    }

    FILE* dest = fopen(argv[2], "wb");
    if (!dest) {
        fprintf(stderr, "Erro: não foi possível abrir %s para escrita\n", argv[2]);
        fclose(src);
        return EXIT_FAILURE;
    }

    char buffer[BUFFER_SIZE];
    size_t nread;

    while ((nread = fread(buffer, 1, BUFFER_SIZE, src)) > 0) {
        fwrite(buffer, 1, nread, dest);
    }

    fclose(src);
    fclose(dest);

    return EXIT_SUCCESS;
}
