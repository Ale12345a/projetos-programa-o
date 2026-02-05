#include <stdio.h>
#include <ctype.h>  // para isdigit()

int algum_digito(char str[]) {
    for (int i = 0; str[i] != '\0'; i++) {
        if (isdigit(str[i])) {
            return 1;  // encontrou um dígito
        }
    }
    return 0;  // nenhum dígito encontrado
}

int main(void) {
    char s1[] = "abc123";
    char s2[] = "HelloWorld";

    printf("%d\n", algum_digito(s1)); // 1
    printf("%d\n", algum_digito(s2)); // 0

    return 0;
}
