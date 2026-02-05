#include <stdio.h>

// Função auxiliar para trocar dois elementos
void trocar(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// Função para encontrar índice da mediana de três valores
int mediana3(int arr[], int l, int m, int u) {
    int a = arr[l], b = arr[m], c = arr[u];
    if ((a <= b && b <= c) || (c <= b && b <= a)) return m;
    if ((b <= a && a <= c) || (c <= a && a <= b)) return l;
    return u;
}

// Partição com pivot mediana de três
int partition(int arr[], int l, int u) {
    int m = (l + u) / 2;
    int pivot_idx = mediana3(arr, l, m, u);
    trocar(&arr[l], &arr[pivot_idx]); // Colocar pivot no início

    int pivot = arr[l];
    int i = l + 1;
    int j = u;

    while (1) {
        while (i <= u && arr[i] <= pivot) i++;
        while (arr[j] > pivot) j--;
        if (i >= j) break;
        trocar(&arr[i], &arr[j]);
    }
    trocar(&arr[l], &arr[j]);
    return j;
}

// Quicksort recursivo
void quicksort(int arr[], int l, int u) {
    if (l < u) {
        int p = partition(arr, l, u);
        quicksort(arr, l, p - 1);
        quicksort(arr, p + 1, u);
    }
}

// Função para imprimir o vetor
void imprimir(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main() {
    int v[] = {9, 2, 7, 4, 1, 8, 5, 3, 6};
    int n = sizeof(v) / sizeof(v[0]);

    printf("Antes da ordenação:\n");
    imprimir(v, n);

    quicksort(v, 0, n - 1);

    printf("Após a ordenação:\n");
    imprimir(v, n);

    return 0;
}
