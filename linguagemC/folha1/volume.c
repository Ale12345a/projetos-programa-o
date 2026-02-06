/*Escreva um programa que leia o valor do raio e calcule o volume de uma esfera usando
a seguinte fórmula: V = 4/3πr3.
Sugestão: na linguagem C não existe operação pré-denida para cálculo de potências;
pode usar multiplicações repetidas, e.g. r*r*r para calcular r3. Tenha ainda o cuidado de
escrever a fração 4/3 como 4.0/3.0 (o que acontece se usar 4/3?)*/

#include <stdio.h>

int main(void) {
   int l, w, h, v;   // dimensões e volume

   l = 11;    // comprimento
   w = 5;     // largura
   h = 6;     // altura
   v = l*w*h; // cálculo do volume

   printf("LxWxH: %d*%d*%d (cm)\n", l,w,h);
   printf("Volume: %d (cm^3)\n", v);
   return 0;
}