import java.util.*;
import java.io.*;

public class bipartidos {
  static int n;              // numero de casos a considerar
  static int ver;            // Numero de nos do grafo
  static boolean adj[][];    // Matriz de adjacencias
  static boolean visited[];  // Que nos ja foram visitados?
  static int visited_cor[];  // cor dos nos que ja foram visitados
  static boolean resultado;

  static void dfs(int v) {
    visited[v] = true;
    if (v==1) visited_cor[v]=0;
    for (int i=1; i<=ver; i++){

      if (adj[v][i] && !visited[i]){
        if (visited_cor[v]==0)
        visited_cor[i]=1;
        if (visited_cor[v]==1)
        visited_cor[i]=0;
        dfs(i);
      }

      if (adj[v][i] && visited[i] && (visited_cor[v]==visited_cor[i]))
      resultado=false;
    }
  }

  public static void main(String args[]) {
    Scanner stdin = new Scanner(System.in);

    n = stdin.nextInt();
    for (int i=0;i<n;i++){
      resultado=true;
      ver = stdin.nextInt();
      adj     = new boolean[ver+1][ver+1];
      visited = new boolean[ver+1];
      visited_cor = new int[ver+1];
      int edges = stdin.nextInt();
      for (int j=0; j<edges; j++) {
        int a = stdin.nextInt();
        int b = stdin.nextInt();
        adj[a][b] = adj[b][a] = true;
      }

      for (int j=1;j<=n;j++)
      if (!visited[j])  dfs(j);

      if (resultado) System.out.println("sim");
      else{
        System.out.println("nao");
      }
    }
  }
}
