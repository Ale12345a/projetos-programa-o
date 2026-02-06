/*Implemente uma função void ordenar(char str[]) que ordena os carateres numa ca-
deia pelos seus códigos. Por exemplo: se str = "ALGORITMO" então após execução devemos
ter str = "AGILMOORT".
Sugestão: cadeias de carateres em linguagem C são variáveis indexadas, pelo que podemos
usar um dos algoritmos de ordenação apresentados nas aulas teóricas (seleção, inserção ou
quicksort). Pode determinar o número de carateres da cadeia usando strlen.*/

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
