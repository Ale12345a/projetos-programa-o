/*Dena uma função void eliminar(char str[], char ch) que elimina a primeira
ocorrência de um carater ch de uma cadeia de carateres. Exemplo: se str = "ABBA", então
depois de executar eliminar(str, 'B') devemos ter str = "ABA".
Tenha o cuidado de colocar corretamente o terminador '\0'.*/

#include <stdio.h>

void eliminar(char str[], char ch) {
    int i = 0;

    // Encontrar a posição do caractere a eliminar
    while (str[i] != '\0' && str[i] != ch) {
        i++;
    }

    // Se encontrou, deslocar todos os seguintes uma posição à esquerda
    if (str[i] == ch) {
        int j = i;
        while (str[j] != '\0') {
            str[j] = str[j+1];
            j++;
        }
    }
}

int main(void) {
    char palavra[] = "ABBA";

    eliminar(palavra, 'B');
    printf("%s\n", palavra); // saída: ABA

    eliminar(palavra, 'A');
    printf("%s\n", palavra); // saída: BA

    return 0;
}
