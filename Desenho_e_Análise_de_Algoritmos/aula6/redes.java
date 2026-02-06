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