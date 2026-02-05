#include <stdio.h>

char *procurar(char *str, char ch) {
    while (*str != '\0') {
        if (*str == ch) {
            return str;
        }
        str++;
    }
    return NULL;
}

int main() {
    char texto[] = "ALGORITMO";
    char c = 'O';
    char *res = procurar(texto, c);

    if (res != NULL)
        printf("Caractere '%c' encontrado na posição: %td\n", c, res - texto);
    else
        printf("Caractere '%c' não encontrado.\n", c);

    return 0;
}
