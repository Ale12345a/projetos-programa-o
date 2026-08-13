//O resultado deve ser o número de espaços na cadeia.

#include <stdio.h>

int contar_espacos(char *str) {
    int contador = 0;
    char *p = str;               // ponteiro para o início da string

    while (*p != '\0') {         // percorre até ao fim da string
        if (*p == ' ') {         // verifica se o caractere atual é um espaço
            contador++;
        }
        p++;                     // avança o ponteiro para o próximo caractere
    }

    return contador;
}

int main() {
    char frase[] = "Exemplo de frase com espacos";

    int total = contar_espacos(frase);
    printf("Número de espaços: %d\n", total);

    return 0;
}
