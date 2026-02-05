#include <stdio.h>

int main() {
    int n;
    int bin[32];  // array para armazenar os dígitos binários (máx. 32 bits)
    int i = 0;

    printf("Digite um número inteiro não-negativo: ");
    scanf("%d", &n);

    if (n == 0) {
        printf("0\n");
        return 0;
    }

    // calcula os dígitos binários (do menos significativo para o mais significativo)
    while (n > 0) {
        bin[i] = n % 2;  // resto da divisão por 2
        n = n / 2;       // divide por 2
        i++;
    }

    // imprime os dígitos na ordem correta (mais significativo primeiro)
    for (int j = i - 1; j >= 0; j--) {
        printf("%d", bin[j]);
    }
    printf("\n");

    return 0;
}
