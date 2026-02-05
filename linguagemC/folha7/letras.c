#include <stdio.h>
#include <ctype.h>  // para isalpha()

int todos_letras(char str[]) {
    for (int i = 0; str[i] != '\0'; i++) {
        if (!isalpha(str[i])) {
            return 0;  // encontrou um caractere que não é letra
        }
    }
    return 1;  // todos são letras
}

int main(void) {
    char s1[] = "HelloWorld";
    char s2[] = "Hello123";

    printf("%d\n", todos_letras(s1)); // 1
    printf("%d\n", todos_letras(s2)); // 0

    return 0;
}
