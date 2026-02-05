#include <stdio.h>
#include <ctype.h>  // para toupper()

int pontos(char c) {
    c = toupper(c); // ignora se é maiúscula ou minúscula
    switch(c) {
        case 'A': case 'E': case 'I': case 'L': 
        case 'N': case 'O': case 'R': case 'T': 
        case 'S': case 'U':
            return 1;
        case 'D': case 'G':
            return 2;
        case 'B': case 'C': case 'M': case 'P':
            return 3;
        case 'F': case 'H': case 'V': case 'W': case 'Y':
            return 4;
        case 'K':
            return 5;
        case 'J': case 'X':
            return 8;
        case 'Q': case 'Z':
            return 10;
        default:
            return 0;  // não é letra
    }
}

int main() {
    int c, total = 0;

    while ((c = getchar()) != EOF) {
        total += pontos(c);
    }

    printf("Pontuação da palavra: %d\n", total);

    return 0;
}
