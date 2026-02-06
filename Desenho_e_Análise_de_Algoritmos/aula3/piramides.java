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
