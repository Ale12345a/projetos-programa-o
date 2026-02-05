#include <stdio.h>

int calc(char str[]) {
    int a = str[0] - '0';
    int b = str[2] - '0';
    char op = str[1];

    if (op == '+') return a + b;
    if (op == '-') return a - b;
    if (op == '*') return a * b;

    return 0; // caso inválido (não esperado segundo o enunciado)
}

int main(void) {
    printf("%d\n", calc("5-3")); // 2
    printf("%d\n", calc("2*3")); // 6
    printf("%d\n", calc("4+7")); // 11

    return 0;
}
