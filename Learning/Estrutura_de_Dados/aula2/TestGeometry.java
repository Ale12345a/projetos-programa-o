// Uma classe simples para representar um ponto 2D
class Point {
   int x, y;

   Point() {
      x = y = 0;
   }
   
   Point(int x0, int y0) {
      x = x0;
      y = y0;
   }

   // Devolve uma representação em texto do conteúdo de um Ponto
   public String toString() {
      return "(" + x + "," + y + ")";
   }
}

class Rectangle {
    // Atributos: canto inferior esquerdo e canto superior direito
    private Point bottomLeft;
    private Point topRight;

    // Construtor com coordenadas inteiras
    Rectangle(int x1, int y1, int x2, int y2) {
        bottomLeft = new Point(x1, y1);
        topRight   = new Point(x2, y2);
    }

    // Construtor com pontos
    Rectangle(Point p1, Point p2) {
        bottomLeft = new Point(p1.x, p1.y);
        topRight   = new Point(p2.x, p2.y);
    }

    // Calcula a área do retângulo
    int area() {
        int width  = topRight.x - bottomLeft.x;
        int height = topRight.y - bottomLeft.y;
        return width * height;
    }

    // Calcula o perímetro do retângulo
    int perimeter() {
        int width  = topRight.x - bottomLeft.x;
        int height = topRight.y - bottomLeft.y;
        return 2 * (width + height);
    }

    // Verifica se um ponto está dentro do retângulo (bordas incluídas)
    boolean pointInside(Point p) {
        return (p.x >= bottomLeft.x && p.x <= topRight.x) &&
               (p.y >= bottomLeft.y && p.y <= topRight.y);
    }

    // Verifica se outro retângulo está completamente dentro deste retângulo
    boolean rectangleInside(Rectangle r) {
        return this.pointInside(r.bottomLeft) &&
               this.pointInside(r.topRight);
    }
}


// Classe principal com a função main
public class TestGeometry {
   public static void main(String[] args) {
      Point a = new Point(1,1);
      Point b = new Point(2,2);
      Point c = new Point(3,4);
      Point d = new Point(8,2);
      
      Rectangle amarelo  = new Rectangle(a, c);
      Rectangle laranja  = new Rectangle(2, 3, 9, 6);
      Rectangle verde    = new Rectangle(3, 4, 4, 5);
      Rectangle azul     = new Rectangle(5, 1, 6, 5);
      Rectangle vermelho = new Rectangle(7, 3, 9, 5);
      
      System.out.println("Perimetro do retangulo amarelo = " + amarelo.perimeter()); // 10
      System.out.println("Perimetro do retangulo laranja = " + laranja.perimeter()); // 20
      
      System.out.println("Area do retangulo amarelo = " + amarelo.area()); // 6
      System.out.println("Area do retangulo laranja = " + laranja.area()); // 21                   
      
      System.out.println("Ponto B dentro rectangulo amarelo? " + amarelo.pointInside(b)); // true
      System.out.println("Ponto D dentro rectangulo amarelo? " + amarelo.pointInside(d)); // false
      
      System.out.println("Retangulo verde dentro do laranja? " + laranja.rectangleInside(verde));       // true
      System.out.println("Retangulo azul dentro do laranja? " + laranja.rectangleInside(azul));         // false
      System.out.println("Retangulo vermelho dentro do laranja? " + laranja.rectangleInside(vermelho)); // true
   }
}