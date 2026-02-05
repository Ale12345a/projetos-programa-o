#include <stdio.h>

// função que verifica se um ano é bissexto
int bissexto(int n) {
    if (n % 400 == 0) return 1;
    else if (n % 100 == 0) return 0;
    else if (n % 4 == 0) return 1;
    else return 0;
}

// função que retorna o próximo ano bissexto a partir de n
int prox_bissexto(int n) {
    while (!bissexto(n)) {  // enquanto n não for bissexto
        n++;                // incrementa o ano
    }
    return n;
}

// exemplo de uso
int main() {
    int ano;

    printf("Digite um ano: ");
    scanf("%d", &ano);

    printf("Próximo ano bissexto a partir de %d: %d\n", ano, prox_bissexto(ano));

    return 0;
}
