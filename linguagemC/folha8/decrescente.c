void selecao_decrescente(int vec[], int size) {
    for (int i = 0; i < size - 1; i++) {
        int max_idx = i;
        for (int j = i + 1; j < size; j++) {
            if (vec[j] > vec[max_idx]) {
                max_idx = j;
            }
        }
        // trocar vec[i] com vec[max_idx]
        int temp = vec[i];
        vec[i] = vec[max_idx];
        vec[max_idx] = temp;
    }
}

#include <stdio.h>

int main(void) {
    int vec[] = {5, 2, 8, 3, 1};
    int size = 5;

    selecao_decrescente(vec, size);

    for (int i = 0; i < size; i++) {
        printf("%d ", vec[i]);
    }
    printf("\n");

    return 0;
}
