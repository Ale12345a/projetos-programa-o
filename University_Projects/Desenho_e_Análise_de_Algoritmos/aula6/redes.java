/*O Problema

Dado um conjunto de pontos de contacto e as suas respectivas ligações, a tua tarefa é descobrir o número de redes diferentes que existem no circuito. A figura seguinte ilustra um circuito com 3 redes.

Input

Na primeira linha vem um número N indicando o número de pontos de contacto existentes. Na segunda linha vem um número L indicando o número de ligações existentes. Seguem-se L linhas, cada uma com um par de números diferentes indicando que pontos de contacto estão conectados pela ligação. Os pontos de contacto são sempre identificados por números de 1 até N. Note que as ligações não vêm por nenhuma ordem em específico e que nunca aparecem ligações repetidas.
Output

Deve ser imprimida uma única linha, contendo o número de redes do respectivo circuito. */

import java.util.Scanner;
import java.util.Arrays;

class redes{
  static boolean[][] g;
  static boolean[] visited;
  static int c=0;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int v = in.nextInt(), e = in.nextInt(), n1, n2;
    g = new boolean[v][v];
    for(int i=0;i<e;i++){
      n1 = in.nextInt()-1;
      n2 = in.nextInt()-1;
      g[n1][n2] = true;
      g[n2][n1] = true;
    }
    visited = new boolean[v];
    //System.out.println(Arrays.deepToString(g));
    for(int i=0;i<visited.length;i++)
      if(!visited[i]){
        subsets(i);
        c++;
      }
    System.out.println(c);
  }

  public static void subsets(int i){
    visited[i] = true;
    for(int j=0;j<visited.length;j++){
      if(g[i][j] && !visited[j]) subsets(j);
    }


  }

}