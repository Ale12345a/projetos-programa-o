public class TestSum {

    // --------------------------------------------------------
    // Versão recursiva 1: dividir entre o primeiro elemento e o resto do array
    static int sumRec1(int v[], int start, int end) {
        if (start > end) return 0;          // caso base: intervalo vazio
        if (start == end) return v[start];  // caso base: 1 elemento
        int sumRest = sumRec1(v, start + 1, end);
        return v[start] + sumRest;
    }

    // --------------------------------------------------------
    // Versão recursiva 2: dividir entre metade esquerda e metade direita
    static int sumRec2(int v[], int start, int end) {
        if (start > end) return 0;          // caso base: intervalo vazio
        if (start == end) return v[start];  // caso base: 1 elemento
        int middle = (start + end) / 2;
        int sumLeft = sumRec2(v, start, middle);
        int sumRight = sumRec2(v, middle + 1, end);
        return sumLeft + sumRight;
    }


public static void main(String[] args) {
      int v[] = {1,5,2,8,4,3,7,6}; // Inicializacao de array

      System.out.println("sumRec1: " + sumRec1(v, 0, v.length - 1));
      System.out.println("sumRec2: " + sumRec2(v, 0, v.length - 1));
   }
}