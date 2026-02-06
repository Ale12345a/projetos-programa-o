/*Dena uma função void sort_desc(int vec[], int n) que ordena um vetor de in-
teiros de comprimento n por ordem descendente (isto é, primeiro o maior valor, depois o
segundo maior, e assim sucessivamente). Por exemplo: se vec={3,2,1,3,5} e n=5 então
depois de executar sort_desc(vec, n) devemos ter vec={5,3,3,2,1}.
Sugestão: adapte um dos algoritmos de ordenação (seleção ou inserção) apresentados
nas aulas 18 e 19 para ordenar por ordem inversa.*/

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
