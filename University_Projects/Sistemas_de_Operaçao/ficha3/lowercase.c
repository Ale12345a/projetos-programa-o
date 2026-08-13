#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

int main(int argc, char* argv[]) {
    if (argc < 2) {
        printf("Uso: %s <string>\n", argv[0]);
        return 1;
    }

    // cria cópia da string recebida
    char *p = strdup(argv[1]);
    if (p == NULL) {
        perror("Erro ao alocar memória");
        return 1;
    }

    // transforma cada caractere em minúscula
    for (int i = 0; p[i] != '\0'; i++) {
        p[i] = tolower((unsigned char)p[i]);
    }

    printf("String em minúsculas: %s\n", p);

    free(p); // libertar memória
    return 0;
}
