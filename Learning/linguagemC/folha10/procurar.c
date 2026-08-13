/*Considere a função apresentada na aula teórica 14 para procurar um carater numa
cadeia. Prentende-se que escreva uma versão alternativa desta função usando apontadores
em vez de índices. A função deve ter a declaração
char *procurar(char *str, char ch);
O resultado deve ser um apontador para a primeira ocorrência do carater ch (se este ocorrer)
ou NULL caso contrário.*/

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
