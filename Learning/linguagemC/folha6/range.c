/*Escreva uma denição da função
void range(int vec[], unsigned size, int inicio, int incr)
que inicializa elementos de um vector vec com size valores inteiros inicio, incio+incr,
incio+2*incr, etc. seguindo uma progressão aritmética. Exemplo:
int a[5];
range(a, 5, 3, 2);  a[] passa a conter { 3, 5, 7, 9, 11 } 
Utilize um ciclo for para percorrer os índices válidos e atribuir valores sucessivos aos ele-
mentos do vector.*/

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
