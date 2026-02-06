/*O Problema

Dado um conjunto de N distâncias, e P perguntas (queries), cada uma indicando um número Ki de dias, a tua tarefa é calcular, para cada pergunta, qual o custo ótimo, ou seja, qual o caminho que minimiza a maior distância num único dia, tal com atrás explicado.
Input

Na primeira linha do input vem um único número indicando N, a quantidade de distâncias a considerar. Segue-se uma linha com N inteiros Di, indicando as distâncias entre locais de acampamento.

Na terceira linha vem um número P, indicando quantidade de perguntas. Seguem-se P linhas, cada uma com um inteiro Ki indicando o número de dias em que o Aniceto deseja dividir o percurso.
Output

O output deve ser constituído por P linhas, uma por cada pergunta, na mesma ordem em que vinham no input. Cada uma das linhas deve indicar o custo ótimo respetivo, ou seja, a menor distância máxima de uma partição em Ki dias das distâncias dadas. */

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
