#include <stdio.h>

int main() {
    int num1, den1, num2, den2;
    int resultado_num, resultado_den;

    // lê a primeira fração
    printf("Primeiro numerador? ");
    scanf("%d", &num1);
    printf("Primeiro denominador? ");
    scanf("%d", &den1);

    // lê a segunda fração
    printf("Segundo numerador? ");
    scanf("%d", &num2);
    printf("Segundo denominador? ");
    scanf("%d", &den2);

    // calcula a soma
    resultado_num = num1 * den2 + num2 * den1;
    resultado_den = den1 * den2;

    // imprime o resultado
    printf("%d/%d + %d/%d = %d/%d\n", num1, den1, num2, den2, resultado_num, resultado_den);

    return 0;
}
