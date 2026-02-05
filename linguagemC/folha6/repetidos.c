#include <stdio.h>

int repetidos(int vec[], unsigned size) {
    for (unsigned i = 0; i < size; i++) {
        for (unsigned j = i + 1; j < size; j++) {
            if (vec[i] == vec[j]) {
                return 1;  // encontrou repetido
            }
        }
    }
    return 0;  // nenhum repetido
}

int main(void) {
    int a[5] = {2, -1, 0, 2, -1};
    int b[5] = {3, 4, 1, 2, -1};

    printf("%d\n", repetidos(a, 5)); // imprime 1
    printf("%d\n", repetidos(b, 5)); // imprime 0

    return 0;
}
