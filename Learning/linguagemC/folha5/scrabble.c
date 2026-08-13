/*No jogo SCRABBLE os jogadores pontuam formando palavras do dicionário com chas
representando letras individuais. A pontuação de cada letra depende da sua raridade no
dicionário. As pontuações para o inglês são: A,E,I,L,N,O,R,T,S,U: 1 ponto; D,G: 2 pontos;
B,C,M,P: 3 pontos; F,H,V,W,Y: 4 pontos; K: 5 pontos; J,X: 8 pontos; Q,Z: 10 pontos. A
pontuação duma palavra é a soma dos pontos de letras individuais.1 Por exemplo: a palavra
PITFALL vale 3 + 1 + 1 + 4 + 1 + 1 + 1 = 12 pontos.
Escreva um programa que lê uma sequência de carateres de uma palavra terminada por EOF
e calcula e imprime a sua pontuação; considere que espaços ou outros carateres não-letras
valem 0 pontos.
Sugestão: dena uma função auxiliar para calcular a pontuação para um carater apenas;
pode ainda usar a instrução switch para selecionar o a pontuação de cada letra.*/

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
