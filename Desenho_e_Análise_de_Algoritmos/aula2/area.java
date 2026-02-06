import java.util.Scanner;
public class area{
  static float cx, cy, r;
  public static void main(String[] args) {
    Scanner stdin = new Scanner(System.in);
    int n = stdin.nextInt();
    for(int i=0;i<n;i++){
      System.out.println(
      intersection(stdin.nextInt(),stdin.nextInt(),stdin.nextInt(),
      stdin.nextInt(),stdin.nextInt(),stdin.nextInt())
      );
    }
  }
  public static float intersection(int ax, int ay, int l, int ccx, int ccy, int rr){
    cx = ccx;
    cy = ccy;
    r = rr;
    return intersection(ax, ay, l);
  }

  private static float intersection(float ax, float ay, float l){
    if ( l < 0.0001 ) return 0;
    else if(!collides(ax, ay, l)) {
      return 0;
    }
    else if(circleInsideSquare(ax, ay, l)) {
      return r*r*3.14f;
    }
    else if(squareInsideCircle(ax, ay, l)) {
      return l*l;
    }
    else{
      return(
      intersection(ax,     ay,     l/2) +
      intersection(ax+l/2, ay,     l/2) +
      intersection(ax,     ay+l/2, l/2)   +
      intersection(ax+l/2, ay+l/2, l/2)
      );
    }
  }


  private static boolean squareInsideCircle(float ax, float ay, float l){
    return  (ax-cx)*(ax-cx)+(ay-cy)*(ay-cy) <= r*r     &&
            (ax+l-cx)*(ax+l-cx)+(ay-cy)*(ay-cy) <= r*r &&
            (ax-cx)*(ax-cx)+(ay+l-cy)*(ay+l-cy) <= r*r &&
            (ax+l-cx)*(ax+l-cx)+(ay+l-cy)*(ay+l-cy) <= r*r;
  }

  private static boolean circleInsideSquare(float ax, float ay, float l){
    float bx = ax + l;
    float by = ay + l;
    float rcxPos = cx + r;
    float rcxNeg = cx - r;
    float rcyPos = cy + r;
    float rcyNeg = cy - r;
    return  (ax <= rcxNeg && rcxNeg <= bx) && (ax <= rcxPos && rcxPos <= bx) &&
            (ay <= rcyNeg && rcyNeg <= by) && (ay <= rcyPos && rcyPos <= by);
  }

  private static boolean collides(float ax, float ay, float l) {
    float testX = cx;
    float testY = cy;
    if (cx < ax)         testX = ax;        // left edge
    else if (cx > ax+l) testX = ax+l;     // right edge

    if (cy < ay)         testY = ay;        // top edge
    else if (cy > ay+l) testY = ay+l;     // bottom edge

    float distX = cx-testX;
    float distY = cy-testY;
    float distance = (float) Math.sqrt( (distX*distX) + (distY*distY) );
    if (distance <= r) return true;
    return false;

  }


}
