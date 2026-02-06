import java.util.*;
import java.io.*;

public class celulas {
  static int rows,cols;
  static char m[][];
  static boolean visited[][];
  static int max;
  static int maybe_max=0;


  static void dfs(int y, int x){
    if (!(y>=0 && y<rows && x>=0 && x<cols)) return ;
    if (visited[y][x] || m[y][x]!='#') return ;
    //System.out.println("dfs( "+y+","+x+")");
    maybe_max++;
    visited[y][x]=true;
    dfs(y-1,x);
    dfs(y+1,x);
    dfs(y,x+1);
    dfs(y,x-1);
    dfs(y+1,x+1);
    dfs(y+1,x-1);
    dfs(y-1,x+1);
    dfs(y-1,x-1);
  }

  public static void main(String args[]) {
    Scanner stdin = new Scanner(System.in);
    int N=stdin.nextInt();
    for (int c=0;c<N;c++){

      max  = 0;
      rows = stdin.nextInt();
      cols = stdin.nextInt();
      String aux;
      m= new char[rows][cols];
      visited= new boolean[rows][cols];
      for (int i=0;i<rows;i++){
        aux=stdin.next();
        //System.out.println(aux);
        for (int j=0;j<cols;j++)
          m[i][j]= aux.charAt(j);
      }

      int count=0;
      for (int i=0;i<rows;i++)
        for (int j=0;j<cols;j++)
          if (m[i][j]=='#' && !visited[i][j]){
            maybe_max=0;
            dfs(i,j);
            //System.out.println("maybe_max "+maybe_max);
            if (maybe_max>max) max=maybe_max;
            //System.out.println("-----------------------");
          }


      //System.out.println(count);
      System.out.println(max);
    }
  }
}
