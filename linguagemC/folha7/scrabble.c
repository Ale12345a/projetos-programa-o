#include <stdio.h>

int scrabble(char str[]) {
    int total = 0;

    for (int i = 0; str[i] != '\0'; i++) {
        switch(str[i]) {
            case 'A': case 'E': case 'I': case 'L': case 'N':
            case 'O': case 'R': case 'S': case 'T': case 'U':
                total += 1; break;
            case 'D': case 'G':
                total += 2; break;
            case 'B': case 'C': case 'M': case 'P':
                total += 3; break;
            case 'F': case 'H': case 'V': case 'W': case 'Y':
                total += 4; break;
            case 'K':
                total += 5; break;
            case 'J': case 'X':
                total += 8; break;
            case 'Q': case 'Z':
                total += 10; break;
        }
    }

    return total;
}

int main(void) {
    printf("%d\n", scrabble("PITFALL"));  // 12
    printf("%d\n", scrabble("HELLO"));    // 8
    printf("%d\n", scrabble("QUIZ"));     // 22

    return 0;
}
