#include <stdio.h>

int main() {
    float valor, comissao;

    while (1) {
        // Ler valor da transferência
        printf("Valor da transferencia (0 para terminar): ");
        scanf("%f", &valor);

        // Condição de paragem
        if (valor == 0) {
            printf("Programa terminado.\n");
            break;
        }

        // Cálculo da comissão
        if (valor < 2500.00f) {
            comissao = 30.00f + valor * 0.017f;
        } else if (valor < 6250.00f) {
            comissao = 56.00f + valor * 0.0066f;
        } else if (valor < 20000.00f) {
            comissao = 76.00f + valor * 0.0034f;
        } else if (valor < 50000.00f) {
            comissao = 100.00f + valor * 0.0022f;
        } else if (valor < 500000.00f) {
            comissao = 155.00f + valor * 0.0011f;
        } else {
            comissao = 255.00f + valor * 0.0009f;
        }

        // Comissão mínima
        if (comissao < 39.00f) {
            comissao = 39.00f;
        }

        // Mostrar resultado
        printf("Comissao: %.2f EUR\n", comissao);
    }

    return 0;
}
