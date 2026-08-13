#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {
    if (argc < 3) {
        printf("Uso: %s <substring> <string>\n", argv[0]);
        return 1;
    }

    char *sub = argv[1];  // substring a procurar
    char *str = argv[2];  // string onde procurar

    int count = 0;
    char *pos = str;
    int sublen = strlen(sub);

    while (*pos) {
        if (strncmp(pos, sub, sublen) == 0) {
            count++;
        }
        pos++; // avança só 1 caracter, permitindo sobreposição
    }

    printf("'%s' ocorre %d vez(es) em '%s'\n", sub, count, str);

    return 0;
}
