
#include <stdio.h>

int main() {
    double valor, valor_com_iva;

    // lê o valor
    printf("Valor sem IVA? ");
    scanf("%lf", &valor);

    // calcula o valor com 23% de IVA
    valor_com_iva = valor * 1.23;

    // imprime o resultado com 2 casas decimais
    printf("Valor com IVA: %.2lf\n", valor_com_iva);

    return 0;
}
