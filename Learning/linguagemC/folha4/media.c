#include <stdio.h>

int main() {
    double n;
    double soma = 0.0;
    int count = 0;

    // lê números até 0
    while (1) {
        scanf("%lf", &n);
        if (n == 0.0) break;  // termina ao ler 0
        soma += n;            // acumula a soma
        count++;              // conta quantos números foram lidos
    }

    // calcula e imprime a média
    if (count > 0) {
        double media = soma / count;
        printf("%.2lf\n", media); // imprime com 2 casas decimais
    } else {
        printf("0.00\n"); // se não houver números
    }

    return 0;
}
