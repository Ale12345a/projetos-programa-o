/*Escreva um programa para contar palavras da entrada-padrão. Considere que as pa-
lavras são sequências de carateres normais (e.g. letras, algarismos ou sinais de pontuação)
separados por carateres brancos (espaços, tabulação ou mudanças de linha, ou seja, ' ',
'\t' ou '\n').
Por exemplo, o texto seguinte contém 13 palavras (note que a sequência --- é considerada
uma palavra):
To be or not to be, that is the question.
--- William Shakespeare
Sugestão: Utilize duas variáveis para guardar os dois últimos carateres lidos; podemos
então contar o número de vezes que um carater normal (i.e., não branco) é seguido de um
carater branco.*/

#include <stdio.h>

int main() {
    int c, prev;
    int palavras = 0;

    prev = ' ';  // começamos como se já tivéssemos um separador

    while ((c = getchar()) != EOF) {
        // se o anterior é "normal" e o atual é "branco", fechamos uma palavra
        if ((prev != ' ' && prev != '\t' && prev != '\n') &&
            (c == ' ' || c == '\t' || c == '\n')) {
            palavras++;
        }
        prev = c;
    }

    // caso o texto não termine em branco, ainda temos uma palavra aberta
    if (prev != ' ' && prev != '\t' && prev != '\n') {
        palavras++;
    }
    printf("Total de palavras: %d\n", palavras);

    return 0;
}
