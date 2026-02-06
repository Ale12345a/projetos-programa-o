/*Dena uma função int scrabble(char str[]) que calcula a pontuação de uma pa-
lavra dada como cadeia de carateres; pode assumir que a cadeia é constituida apenas por
letras maiúsculas sem acentos.
Recorde que a pontuação de palavras em inglês no jogo SCRABBLE : A,E,I,L,N,O,R,T,S,U:
1 ponto; D,G: 2 pontos; B,C,M,P: 3 pontos; F,H,V,W,Y: 4 pontos; K: 5 pontos; J,X: 8 pon-
tos; Q,Z: 10 pontos. A pontuação duma palavra é a soma dos pontos de letras individuais.1
Exemplo: PITFALL vale 3 + 1 + 1 + 4 + 1 + 1 + 1 = 12 pontos, logo scrabble("PITFALL")
dá resultado 12.*/

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
