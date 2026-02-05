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
