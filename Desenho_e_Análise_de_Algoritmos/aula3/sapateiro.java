/*O Problema

Dado um conjunto de N encomendas de sapatos, cada um com a respectiva duração e multa por dia, a tua tarefa é determinar qual a ordem em que o sapateiro deve tratar das encomendas de modo a pagar a menor multa possível.
Input

Na primeira linha do input vem um número N indicando o número de encomendas a processar.

Seguem-se N linhas, cada uma indicando a i-ésima encomenda. Cada uma destas linhas tem dois inteiros: Di e Mi, indicando respectivamente a duração e multa por dia do i-ésimo par de sapatos. Nota que as encomendas são "numeradas" de 1 até N: a primeira é a 1, a segunda a 2, etc.
Output

O output deve ser constituído pela sequência de encomendas a processar de modo a minimizar a multa a pagar. Uma encomenda é representada pelo seu número no input, pelo que o output é uma permutação dos números de 1 até N. Os números da sequência devem vir separados por um espaço.

Se existirem múltiplas soluções que dêm origem à mesma multa, imprima a solução que seja menor lexicograficamente, isto é, que comece pelo número mais baixo, em caso de empate pelo 2º número mais baixo e por aí adiante. */

import java.util.Scanner;
import java.util.Arrays;

class sapateiro{
  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    Order[] orders = new Order[stdin.nextInt()];
    for(int i = 0; i<orders.length;i++){
      orders[i] = new Order(stdin.nextInt(),stdin.nextInt());
    }
    //System.out.println(Arrays.toString(orders));
    Arrays.sort(orders,
      new java.util.Comparator<Order>(){
        public int compare(Order a, Order b) {
          if(a.ratio < b.ratio) return 1;
          if(a.ratio > b.ratio) return -1;
          return 0;
        }
      }
    );
    //System.out.println(Arrays.toString(orders));
    for(int i=0;i<orders.length-1;i++) System.out.print(orders[i].p + " ");
    System.out.println(orders[orders.length-1].p);
  }

}

class Order{
  static int size=1;
  int days, p;
  float ratio;
  Order(int days, int fee){
    this.days = days;
    ratio = (float)fee / days;
    p=size;
    ++size;
  }
  public String toString(){ return p+":"+"["+days+","+ratio+"]"; }

}
