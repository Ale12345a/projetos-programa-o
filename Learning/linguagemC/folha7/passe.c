/*Escreva uma função int forte(char str[]) que verica se uma cadeia de carateres
é uma palavra passe forte usando o seguinte critério:
1. deve ter pelo menos 6 carateres;
2. deve conter pelo menos uma letra maíscula, uma letra minúscula e um algarismo.
O resultado da função deve ser 1 se ambos os critérios se vericam e 0 caso contrário. Por
exemplo: "Abr4cadabra" e "Apric0t" são palavras-passe fortes, mas "Ub40" não é (porque
o comprimento é inferior a 6) e "POLICE" também não (porque só tem letras maísculas).
Sugestão: pode usar a função strlen da biblioteca-padrão para calcular o comprimento
da cadeia e as funções islower, isupper, isdigit para testar os três tipos de carateres.*/

#include <stdio.h>
#include <string.h>
#include <ctype.h>

int forte(char str[]) {
    if (strlen(str) < 6) {
        return 0; // critério 1: comprimento mínimo
    }

    int tem_maiuscula = 0;
    int tem_minuscula = 0;
    int tem_digito = 0;

    for (int i = 0; str[i] != '\0'; i++) {
        if (isupper(str[i])) tem_maiuscula = 1;
        if (islower(str[i])) tem_minuscula = 1;
        if (isdigit(str[i])) tem_digito = 1;
    }

    if (tem_maiuscula && tem_minuscula && tem_digito) {
        return 1; // todos os critérios satisfeitos
    } else {
        return 0;
    }
}

int main(void) {
    char *senhas[] = {"Abr4cadabra", "Apric0t", "Ub40", "POLICE"};
    
    for (int i = 0; i < 4; i++) {
        printf("%s -> %d\n", senhas[i], forte(senhas[i]));
    }

    return 0;
}
