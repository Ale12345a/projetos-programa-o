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
