#include <stdio.h>

void range(int vec[], unsigned size, int inicio, int incr) {
    for (unsigned i = 0; i < size; i++) {
        vec[i] = inicio + i * incr;
    }
}

int main(void) {
    int a[5];
    range(a, 5, 3, 2);  // a = {3, 5, 7, 9, 11}

    for (int i = 0; i < 5; i++) {
        printf("%d ", a[i]);
    }
    printf("\n");
    return 0;
}
