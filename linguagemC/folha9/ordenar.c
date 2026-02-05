#include <stdio.h>
#include <string.h>

void ordenar(char str[]) {
    int n = strlen(str);

    for (int i = 0; i < n - 1; i++) {
        int min_idx = i;
        for (int j = i + 1; j < n; j++) {
            if (str[j] < str[min_idx]) {
                min_idx = j;
            }
        }
        // trocar str[i] com str[min_idx]
        char temp = str[i];
        str[i] = str[min_idx];
        str[min_idx] = temp;
    }
}

int main(void) {
    char texto[] = "ALGORITMO";
    ordenar(texto);
    printf("String ordenada: %s\n", texto);  // saída: AGILMOORT
    return 0;
}
