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
