/*Escreva uma função int decimal(char str[]) que converte uma cadeia de cara-
teres com algarismos de 0 a 9 no valor inteiro decimal correspondente. Por exemplo:
decimal("1234") deve dar retornar o inteiro 1234.*/

#include <stdio.h>

int decimal(char str[]) {
    int resultado = 0;

    for (int i = 0; str[i] != '\0'; i++) {
        resultado = resultado * 10 + (str[i] - '0');
    }

    return resultado;
}

int main(void) {
    char num1[] = "1234";
    char num2[] = "007";

    printf("%d\n", decimal(num1)); // 1234
    printf("%d\n", decimal(num2)); // 7

    return 0;
}
