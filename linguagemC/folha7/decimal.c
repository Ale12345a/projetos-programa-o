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
