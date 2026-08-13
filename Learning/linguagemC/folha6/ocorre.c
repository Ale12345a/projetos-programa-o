/*Escreva uma função int ocorre(int vec[], int size, int val) que procura um
valor val no elementos de um vector vec. Se o valor ocorre como um dos elementos do
vector, o resultado deve ser 1; caso contrário deve ser 0.*/

#include <stdio.h>

int ocorre(int vec[], int size, int val) {
    for (int i = 0; i < size; i++) {
        if (vec[i] == val) {
            return 1; // valor encontrado
        }
    }
    return 0; // valor não encontrado
}

int main(void) {
    int a[] = {3, 5, 7, 9, 11};
    int n = sizeof(a) / sizeof(a[0]);

    int x = 7;
    if (ocorre(a, n, x))
        printf("%d ocorre no vetor\n", x);
    else
        printf("%d não ocorre no vetor\n", x);

    x = 4;
    if (ocorre(a, n, x))
        printf("%d ocorre no vetor\n", x);
    else
        printf("%d não ocorre no vetor\n", x);

    return 0;
}
