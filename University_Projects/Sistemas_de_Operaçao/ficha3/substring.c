#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {
    if (argc < 3) {
        printf("Uso: %s <string1> <string2>\n", argv[0]);
        return 1;
    }

    char *s1 = argv[1]; // string a procurar
    char *s2 = argv[2]; // string onde procurar

    // strstr devolve um ponteiro para a primeira ocorrência de s1 em s2
    if (strstr(s2, s1) != NULL) {
        printf("'%s' ocorre em '%s'\n", s1, s2);
    } else {
        printf("'%s' NAO ocorre em '%s'\n", s1, s2);
    }

    return 0;
}
