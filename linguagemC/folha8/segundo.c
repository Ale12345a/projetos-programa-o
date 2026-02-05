int segundo_menor(int vec[], int size) {
    // primeira iteração: encontrar o menor e colocar em vec[0]
    for (int i = 0; i < 2; i++) { // apenas duas primeiras iterações
        int min_idx = i;
        for (int j = i + 1; j < size; j++) {
            if (vec[j] < vec[min_idx]) {
                min_idx = j;
            }
        }
        // trocar vec[i] com vec[min_idx]
        int temp = vec[i];
        vec[i] = vec[min_idx];
        vec[min_idx] = temp;
    }

    // o segundo menor valor está agora em vec[1]
    return vec[1];
}

#include <stdio.h>

int main(void) {
    int a[] = {5, 2, 8, 3, 1};
    int size = 5;

    int segundo = segundo_menor(a, size);
    printf("Segundo menor: %d\n", segundo); // saída: 2

    return 0;
}
