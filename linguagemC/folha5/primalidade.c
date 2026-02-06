/*Usando a função de teste de primalidade apresentada na aula 7, escreva um programa
que imprime uma lista de primos até um limite superior especicado pelo utilizador. Exem-
plo:
Limite superior? 100
2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97*/

#include <stdio.h>
#include <math.h>

// Função que testa se um número é primo
int primo(int n) {
    if (n < 2) return 0;       // 0 e 1 não são primos
    if (n == 2) return 1;      // 2 é primo
    if (n % 2 == 0) return 0;  // múltiplos de 2 (exceto 2) não são primos

    // só precisamos de verificar até à raiz quadrada de n
    for (int i = 3; i <= sqrt(n); i += 2) {
        if (n % i == 0) return 0;
    }
    return 1;
}

int main() {
    int limite;

    printf("Limite superior? ");
    scanf("%d", &limite);

    for (int i = 2; i <= limite; i++) {
        if (primo(i)) {
            printf("%d ", i);
        }
    }
    printf("\n");

    return 0;
}
