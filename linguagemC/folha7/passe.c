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
