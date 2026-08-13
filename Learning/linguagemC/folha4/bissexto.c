#include <stdio.h>

// função que verifica se um ano é bissexto
int bissexto(int n) {
    if (n % 400 == 0) {
        return 1; // múltiplo de 400 → bissexto
    } else if (n % 100 == 0) {
        return 0; // múltiplo de 100 mas não de 400 → não bissexto
    } else if (n % 4 == 0) {
        return 1; // múltiplo de 4 → bissexto
    } else {
        return 0; // todos os outros → não bissexto
    }
}

int prox_bissexto(int n) {
    while (!bissexto(n)) {
        n++;
    }
    return n;
}

// exemplo de uso
int main() {
    int ano;
    
    printf("Digite um ano: ");
    scanf("%d", &ano);
    
    if (bissexto(ano)) {
        printf("%d é bissexto.\n", ano);
    } else {
        printf("%d não é bissexto.\n", ano);
    }
    
    int proximo = prox_bissexto(ano);
    printf("O proximo ano bissexto (ou o proprio) e: %d\n", proximo);

    return 0;
}
