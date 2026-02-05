#include <stdio.h>
#include <ctype.h>  // para toupper()

void capitalizar(char str[]) {
    for (int i = 0; str[i] != '\0'; i++) {
        str[i] = toupper(str[i]);
    }
}

int main(void) {
    char frase[] = "Olá, Mundo! 123";

    capitalizar(frase);

    printf("%s\n", frase);  // saída: "OLÁ, MUNDO! 123"

    return 0;
}
