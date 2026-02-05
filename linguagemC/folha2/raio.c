#include <stdio.h>

int main() {
    double r, V;  // usamos double para números com casas decimais

    // lê o raio
    printf("Digite o valor do raio: ");
    scanf("%lf", &r);

    // calcula o volume
    V = (4.0/3.0) * 3.14159265359 * r * r * r;

    // imprime o resultado
    printf("O volume da esfera é: %.2lf\n", V);

    return 0;
}
