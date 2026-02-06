/*Escreva uma função
void decompor(int total_seg, int *horas, int *mins, int *segs);
que decompõe um total inteiro de segundos total_seg em horas, minutos (059) e segundos
(059); os resultados devem ser atribuidos ao conteúdo dos apontadores horas, mins e segs.
Pode assumir que o total de segundos é maior que zero*/

#include <stdio.h>

void decompor(int total_seg, int *horas, int *mins, int *segs) {
    *horas = total_seg / 3600;        // calcula o número de horas
    total_seg %= 3600;                // restante em segundos após as horas
    *mins = total_seg / 60;           // calcula o número de minutos
    *segs = total_seg % 60;           // restante são os segundos
}

// Exemplo de uso
int main() {
    int total = 3671; // exemplo: 1 hora, 1 minuto e 11 segundos
    int h, m, s;

    decompor(total, &h, &m, &s);

    printf("%d segundos = %d horas, %d minutos, %d segundos\n", total, h, m, s);

    return 0;
}
