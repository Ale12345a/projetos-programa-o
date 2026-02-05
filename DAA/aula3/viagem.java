import java.util.*;
public class viagem {

  static int N;
  static int[] D;

  public static int binsear(int low,int high,int key){
    int middle = low + (high-low)/2;
    while(low < high){
      middle = low + (high-low)/2;
      //System.out.println(low+" "+middle+" "+high);
      if(particoes(key,D,N,middle)==1) high=middle;
      //else if(middle==low && particoes(key,D,N,low)==1) return low;
      else low = middle+1;
    }
    return low;
  }

  public static int particoes(int part,int[] dist, int size,int key){
    int count=1;
    int percorrido=0;
    for (int i=0;i<size;i++){
      percorrido+=dist[i];
      if (percorrido==key && i!=size-1){
        count++;
        percorrido=0;
      }
      else if (percorrido>key){
        count++;
        percorrido=dist[i];
      }
      //System.out.println(count+" "+part);
    }
    if (count>part) return -1;
    return 1;
  }

  public static void main(String[] args) {
  	Scanner in = new Scanner(System.in);

    int max=0;
    N= in.nextInt();
    D= new int [N];
    for (int i=0;i<N;i++){
      D[i]= in.nextInt();
      max+=D[i];
    }

    int P= in.nextInt();
    int[] K= new int [P];
    for (int i=0;i<P;i++)
      K[i]= in.nextInt();
    for (int i=0;i<P;i++){
      System.out.println(binsear(1,max,K[i]));
    }
    //System.out.println(particoes(5,D,N,18));
  }
}
