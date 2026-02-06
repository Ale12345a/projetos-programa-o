import java.util.*;

public class moedas{
  public static int N;
  public static int [] Ti;
  public static int P;
  public static int[] Qi;
  public static int[] use;



  public static void calcular(int x,int[] vec){
    vec[0]=0;
    for (int i=1;i<=x;i++){
      vec[i]=10001;
      for (int j=0;j<N;j++){
        if(Ti[j]<=i && 1+vec[i-Ti[j]]< vec[i]){
          vec[i]= 1+vec[i-Ti[j]];
          use[i]=Ti[j];
        }
      }
    }
  }

  public static int max(int[] x,int size){
    int maior=x[0];
    for (int i=0;i<size;i++){
      if (x[i]>maior) maior=x[i];
    }
    return maior;
  }

  public static void main(String[] args){
    Scanner in= new Scanner(System.in);

    N= in.nextInt();
    Ti= new int[N];
    for (int i=0;i<N;i++)
      Ti[i]=in.nextInt();

    P= in.nextInt();
    Qi= new int[P];
      for (int i=0;i<P;i++){
        Qi[i]=in.nextInt();
      }

    int maior=max(Qi,P);
    use= new int[maior+1];
    int[] coins= new int[maior+1];

    calcular(maior,coins);

    /*for (int i=0;i<P;i++){
      FastPrint.out.println(coins[Qi[i]]);
    }*/
    int p;
    for(int i=0;i<Qi.length;i++){
      p = Qi[i];
      System.out.print(p+": ");
      System.out.print("["+ coins[p] +"]");
      while(p>0){
        System.out.print(" " + use[p]);
        p -= use[p];
      }
      System.out.println();
    }
  }
}