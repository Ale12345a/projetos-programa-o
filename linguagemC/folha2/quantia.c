#include <stdio.h>

int main() {
    int quantia;
    int notas20, notas10, notas5, moedas1;

    // lê a quantia
    printf("Quantia em EUR? ");
    scanf("%d", &quantia);

    // calcula o número de notas e moedas
    notas20 = quantia / 20;
    quantia = quantia % 20;  // resto após retirar notas de 20

    notas10 = quantia / 10;
    quantia = quantia % 10;  // resto após retirar notas de 10

    notas5 = quantia / 5;
    quantia = quantia % 5;   // resto após retirar notas de 5

    moedas1 = quantia;        // o que sobra são moedas de 1

    // imprime o resultado
    printf("notas EUR 20: %d\n", notas20);
    printf("notas EUR 10: %d\n", notas10);
    printf("notas EUR 5: %d\n", notas5);
    printf("moedas EUR 1: %d\n", moedas1);

    return 0;
}
