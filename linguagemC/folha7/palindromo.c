/*Escreva uma função int palindromo(char str[]) que testa se uma cadeia de cara-
teres é um palíndromo, isto é, se tem a mesma sequência de carateres da esquerda para a
direita e vice-versa.*/

#include <stdio.h>
#include <string.h>  // para strlen()

int palindromo(char str[]) {
    int n = strlen(str);
    for (int i = 0; i < n / 2; i++) {
        if (str[i] != str[n - 1 - i]) {
            return 0;  // não é palíndromo
        }
    }
    return 1;  // é palíndromo
}

int main(void) {
    char s1[] = "radar";
    char s2[] = "computador";

    printf("%d\n", palindromo(s1)); // 1
    printf("%d\n", palindromo(s2)); // 0

    return 0;
}
