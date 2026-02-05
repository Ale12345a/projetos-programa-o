#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 10   // número de perguntas (fácil de modificar)

int main(void) {
    int certo = 0, errado = 0;
    int a, b, resposta, resultado;

    // inicializar gerador de aleatórios
    srand(time(NULL));

    for (int i = 0; i < N; i++) {
        a = rand() % 9 + 1;  // número entre 1 e 9
        b = rand() % 9 + 1;

        printf("Quanto é %d x %d? ", a, b);
        scanf("%d", &resposta);

        resultado = a * b;
        if (resposta == resultado) {
            printf("Certo!\n");
            certo++;
        } else {
            printf("Errado! O resultado é %d.\n", resultado);
            errado++;
        }
    }

    printf("\nResumo: %d certas, %d erradas.\n", certo, errado);

    return 0;
}
