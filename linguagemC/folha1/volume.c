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