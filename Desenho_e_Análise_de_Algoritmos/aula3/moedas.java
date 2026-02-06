/*O Problema

Escreva um programa que dado um conjunto de N moedas e uma série de P perguntas, cada uma indicando uma quantia Qi, indique qual o menor número de moedas necessário para fazer cada quantia, e quais as moedas a usar em cada caso.

Pode assumir que todas as quantias são possíveis de fazer e que tem uma quantidade "infinita" de cada moeda, ou seja que para fazer o mínimo pode repetir qualquer valor de moeda tantas vezes quanto o necessário.
Input

Na primeira linha vem um número N, indicando o número de tipos de moedas em Algoland. Na segunda linha vêm N inteiros Ti, indicando quais os valores de cada tipo de moeda. Pode assumir que as moedas vêm por ordem crescente.

Na terceira linha vem um único inteiro P indicando o número de perguntas a considerar. Nas P linhas seguintes vêm as perguntas em si, cada uma com um inteiro Qi indicando a quantia para a qual se quer minimizar o número de moedas a usar.
Output

P linhas, cada uma com a resposta da pergunta respectiva. Cada linha deve vir no formato "Qi: [MIN] M1 M2 ... MMIN", onde MIN é o número mínimo de moedas a usar para fazer a quantia Qi e M1 M2 ... MMIN são as MIN moedas a usar para fazer a quantia.

As moedas devem vir por ordem crescente (M1 ≤ M2 ≤ ... ≤ MMIN). Como podem existir várias maneiras diferentes (e mínimas) de fazer cada quantia, deve escrever aquela que use a mais pequena moeda M1; em caso de empate a que use a mais pequena M2 e por aí adiante. */

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