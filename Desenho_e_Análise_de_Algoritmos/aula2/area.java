/*O Problema

Dadas as coordenadas de um quadrado e um círculo a tua tarefa é descobrir qual a área da zona da interseção entre ambas as duas figuras geométricas.
Input

Na primeira linha do input vem um número N, indicando o número de casos a considerar.

Seguem-se N linhas, cada uma com um caso de teste (um quadrado e um círculo). Cada caso é indicado por 6 inteiros: qx, qy, ql, cx, cy, cr. O quadrado tem os lados paralelos aos eixos das coordenadas, canto inferior esquerdo (qx, qy) e lado de tamanho ql. O círculo tem centro (cx, cy) e raio cr.
Output

O output deve ser constituído por N linhas, uma por cada caso, indicando a área da interseção entre o quadrado e o círculo respectivo.

Para ser considerada correcta, a diferença entre a área que escreveu e a área correcta deve ser inferior ou igual a 0.1 (este problema tem um avaliador especial para verificar se isso acontece). Por exemplo, se a resposta correcta for "1.2345" qualquer uma das seguintes respostas seria aceite: "1.2", "1.3, "1.23", "1.22", "1.24" (já "1.1" não seria aceite, pois |1.2345 - 1.1| > 0.1). */

import java.util.Scanner;
public class area{
  static float cx, cy, r;
  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    for(int i=0;i<n;i++){
      System.out.println(
      intersection(stdin.nextInt(),stdin.nextInt(),stdin.nextInt(),
      stdin.nextInt(),stdin.nextInt(),stdin.nextInt())
      );
    }
  }
  public static float intersection(int ax, int ay, int l, int ccx, int ccy, int rr){
    cx = ccx;
    cy = ccy;
    r = rr;
    return intersection(ax, ay, l);
  }

  private static float intersection(float ax, float ay, float l){
    if ( l < 0.0001 ) return 0;
    else if(!collides(ax, ay, l)) {
      return 0;
    }
    else if(circleInsideSquare(ax, ay, l)) {
      return r*r*3.14f;
    }
    else if(squareInsideCircle(ax, ay, l)) {
      return l*l;
    }
    else{
      return(
      intersection(ax,     ay,     l/2) +
      intersection(ax+l/2, ay,     l/2) +
      intersection(ax,     ay+l/2, l/2)   +
      intersection(ax+l/2, ay+l/2, l/2)
      );
    }
  }


  private static boolean squareInsideCircle(float ax, float ay, float l){
    return  (ax-cx)*(ax-cx)+(ay-cy)*(ay-cy) <= r*r     &&
            (ax+l-cx)*(ax+l-cx)+(ay-cy)*(ay-cy) <= r*r &&
            (ax-cx)*(ax-cx)+(ay+l-cy)*(ay+l-cy) <= r*r &&
            (ax+l-cx)*(ax+l-cx)+(ay+l-cy)*(ay+l-cy) <= r*r;
  }

  private static boolean circleInsideSquare(float ax, float ay, float l){
    float bx = ax + l;
    float by = ay + l;
    float rcxPos = cx + r;
    float rcxNeg = cx - r;
    float rcyPos = cy + r;
    float rcyNeg = cy - r;
    return  (ax <= rcxNeg && rcxNeg <= bx) && (ax <= rcxPos && rcxPos <= bx) &&
            (ay <= rcyNeg && rcyNeg <= by) && (ay <= rcyPos && rcyPos <= by);
  }

  private static boolean collides(float ax, float ay, float l) {
    float testX = cx;
    float testY = cy;
    if (cx < ax)         testX = ax;        // left edge
    else if (cx > ax+l) testX = ax+l;     // right edge

    if (cy < ay)         testY = ay;        // top edge
    else if (cy > ay+l) testY = ay+l;     // bottom edge

    float distX = cx-testX;
    float distY = cy-testY;
    float distance = (float) Math.sqrt( (distX*distX) + (distY*distY) );
    if (distance <= r) return true;
    return false;

  }


}
