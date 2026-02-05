#include <stdio.h>
#include <math.h>  // para M_PI

double aprox_pi(int n) {
    double soma = 0.0;
    for (int i = 0; i < n; i++) {
        if (i % 2 == 0)  // termos pares positivos
            soma += 1.0 / (2*i + 1);
        else             // termos ímpares negativos
            soma -= 1.0 / (2*i + 1);
    }
    return 4.0 * soma;
}

int main() {
    int termos[] = {10, 100, 1000};
    int n_termos = sizeof(termos) / sizeof(termos[0]);
    
    for (int i = 0; i < n_termos; i++) {
        int n = termos[i];
        double resultado = aprox_pi(n);
        printf("Aproximação com %d termos: %.15f\n", n, resultado);
    }
    
    printf("Valor real de M_PI: %.15f\n", M_PI);
    
    return 0;
}
