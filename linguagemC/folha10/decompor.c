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
