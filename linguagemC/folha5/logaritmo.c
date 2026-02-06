/*Pretende-se calcular o logaritmo natural (isto é, de base e) usando a série de Taylor:
log(1 + x) = x − x2
2 + x3
3 − · · · =
∞∑
i=1
(−1)i+1 xi
i
Escreva uma função double serie_log(double x, int n) que calcula aproximamente a
série acima somando os termos até à potência n de x. Por exemplo: serie_log(x, 3) deve
calcular x − x2/2 + x3/3. Pode assumir que n ≥ 1. Tenha o cuidado de evitar o cálculo
desnessário de potências sucessivas de x.*/

#include <stdio.h>

double serie_log(double x, int n) {
    double soma = 0.0;
    double pot = x; // começa em x^1
    
    for (int i = 1; i <= n; i++) {
        if (i % 2 == 1) // i ímpar → sinal positivo
            soma += pot / i;
        else            // i par → sinal negativo
            soma -= pot / i;

        pot *= x; // calcula x^(i+1) a partir de x^i
    }
    
    return soma;
}

int main() {
    double x;
    int n;
    
    printf("Valor de x (|x| < 1): ");
    scanf("%lf", &x);
    printf("Número de termos n: ");
    scanf("%d", &n);
    
    double resultado = serie_log(x, n);
    printf("Aproximação de log(1 + %.4f) com %d termos = %.10f\n", x, n, resultado);
    
    return 0;
}
