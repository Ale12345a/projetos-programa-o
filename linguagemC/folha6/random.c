/*Escreva um programa para jogar Advinha o Número: o computador escolhe aleatoria-
mente um número inteiro secreto entre 1 e 1000. Em seguida pede ao jogador humano que
introduza uma tentativa; se a tentativa for menor que número secreto escreve Demasiado
baixo!; se for maior, escrever Demasiado alto!. Enquanto o jogador não acertar no
número secreto vai pedido novas tentativas. Quando o jogador acertar, escreve Acertou
em ... tentativas! e termina o programa.
(Depois de resolver o exercício pense nas seguintes questões: Qual é a melhor estratégia para
escolher a próxima tentativa? E qual é o número mínimo de tentativas tal que garantida-
mente consegue acertar no número secreto?)*/

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
