/*Escreva uma função int desordem(int vec[], int size) que conta quantos pares
de valores numa variável indexada estão fora de ordem, isto é, vec[i] > vec[i + 1]. Exemplo:
se vec = {3, 1, 2, 2, 4, 0} e size=6 então o resultado deve ser 2 (porque 3 6 ≤ 1 e
4 6 ≤ 0).
Note ainda que se a sequência estiver por ordem ascendente, então o resultado é 0 e se
estiver por ordem descente, então o resultado é size − 1.*/

#include <stdio.h>

int desordem(int vec[], int size) {
    int contador = 0;

    for (int i = 0; i < size - 1; i++) {
        if (vec[i] > vec[i + 1]) {
            contador++;
        }
    }

    return contador;
}

int main(void) {
    int a[] = {3, 1, 2, 2, 4, 0};
    int b[] = {1, 2, 3, 4, 5};
    int c[] = {5, 4, 3, 2, 1};

    printf("%d\n", desordem(a, 6)); // saída: 2
    printf("%d\n", desordem(b, 5)); // saída: 0
    printf("%d\n", desordem(c, 5)); // saída: 4

    return 0;
}
