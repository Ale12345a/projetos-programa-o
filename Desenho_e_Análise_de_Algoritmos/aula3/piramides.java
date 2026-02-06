/*O Problema

Escreva um programa que dada uma pirâmide com N camadas, e uma descrição das pedras em falta ou muito deterioradas numa das faces da pirâmide, calcule o número de maneiras diferentes de subir a pirâmide até ao topo, começando por uma qualquer das pedras da primeira camada, evitando as pedras que estão em falta ou muito deterioradas.
Input

Na primeira linha vem o número N que representa o número de pedras na primeira camada e também o número de níveis da pirâmide. Na segunda linha vem o número D de pedras em falta ou muito deterioradas. Nas D linhas seguintes vêm dois números, Ci e Pi, que descrevem cada uma destas pedras em falta ou muito deterioradas: C representa a camada (como indicado na figura), e Pi representa a posição da pedra nessa camada (onde 1 é a pedra mais à esquerda, 2 a 2ª pedra mais esquerda, etc).
Output

Uma única linha com indicando M, o número de maneiras diferente de subir a pirâmide. */

import java.util.Scanner;
import java.util.Arrays;
class piramides{
  static long[][] pir;
  static int[][] loose;
  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    pir = new long[n][n];
    loose = new int[stdin.nextInt()][2];

    for(int i = 0;i<loose.length;i++){
      loose[i][0] = stdin.nextInt()-1;
      loose[i][1] = stdin.nextInt()-1;
    }
    // System.out.println(Arrays.deepToString(loose));
    for(int i=0;i<pir.length;i++) pir[0][i] = 1;
    // for(int i=pir.length-1;i>=0;i--) System.out.println(Arrays.toString(pir[i]));
    // System.out.println();

    for(int i=1;i<n;i++){
      for(int j=0;j<pir.length-i;j++){
        pir[i][j] = bounds(i-1,j) + bounds(i-1,j+1);
      }
    }
    // for(int i=pir.length-1;i>=0;i--) System.out.println(Arrays.toString(pir[i]));
    if (bounds(pir.length-1,0) != 0) System.out.println(pir[pir.length-1][0]);
    else System.out.println(0);
  }
  public static long bounds(int i, int j){
    for(int k=0;k<loose.length;k++)
      if(i == loose[k][0] && j == loose[k][1]) return 0;
    return pir[i][j];
  }
}
