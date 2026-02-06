/*Escreva uma função int ordenada(int vec[], int size) que testa se uma variável
indexada de inteiros está por ordem ascendente em sentido lato, isto é, se vec[i] ≤ vec[i + 1]
para todos os índices i de 0 a size − 2. O resultado deve ser 1 em caso armativo e 0 em
caso negativo. A função não deve modicar os valores da variável indexada.
Exemplos: se vec = {1, 3, 3, 5, 6} então ordenada(vec, 5) deve retornar 1; se
vec = {1, 3, 2, 5, 6} então ordenada(vec, 5) deve retornar 0.*/

#include <stdio.h>

int ordenada(int vec[], int size) {
    for (int i = 0; i < size - 1; i++) {
        if (vec[i] > vec[i + 1]) {
            return 0; // vetor não está ordenado
        }
    }
    return 1; // vetor está ordenado
}

int main(void) {
    int a[] = {1, 3, 3, 5, 6};
    int b[] = {1, 3, 2, 5, 6};

    printf("%d\n", ordenada(a, 5)); // saída: 1
    printf("%d\n", ordenada(b, 5)); // saída: 0

    return 0;
}
