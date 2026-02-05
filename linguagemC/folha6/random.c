#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(void) {
    int segredo, tentativa, tentativas = 0;

    // inicializar gerador de aleatórios
    srand(time(NULL));
    segredo = rand() % 1000 + 1;  // número entre 1 e 1000

    printf("Tente adivinhar o número (entre 1 e 1000):\n");

    do {
        printf("A sua tentativa: ");
        scanf("%d", &tentativa);
        tentativas++;

        if (tentativa < segredo) {
            printf(" baixo!\n");
        } else if (tentativa > segredo) {
            printf(" alto!\n");
        } else {
            printf("Acertou em %d tentativa(s)!\n", tentativas);
        }
    } while (tentativa != segredo);

    return 0;
}
