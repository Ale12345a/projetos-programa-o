#include <stdio.h>

int comparar(char *str1, char *str2) {
    // percorre ambas as cadeias enquanto os caracteres forem iguais
    while (*str1 != '\0' && *str2 != '\0') {
        if (*str1 != *str2)
            return 0; // diferente
        str1++;
        str2++;
    }
    // se ambas chegaram ao final, são iguais
    return (*str1 == '\0' && *str2 == '\0') ? 1 : 0;
}

int main() {
    char texto1[] = "ALGORITMO";
    char texto2[] = "ALGORITMO";
    char texto3[] = "ALGORITMA";

    printf("texto1 e texto2: %d\n", comparar(texto1, texto2)); // 1
    printf("texto1 e texto3: %d\n", comparar(texto1, texto3)); // 0

    return 0;
}
