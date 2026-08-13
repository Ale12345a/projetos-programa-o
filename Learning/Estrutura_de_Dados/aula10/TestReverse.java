// -----------------------------------------------------------
// Estruturas de Dados 2019/2020 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados1920/
// -----------------------------------------------------------
// Invertendo um array (versao recursiva)
// Ultima alteracao: 21/04/2018
// -----------------------------------------------------------

import java.util.Arrays;

public class TestReverse {

   // Inverter array v entre posicoes start e end
   static void reverse(int v[], int start, int end) {
      if (start>=end) return;     // Caso base: array de tamanho < 2
      int tmp = v[start];         // Trocar primeiro com ultimo
      v[start] = v[end];
      v[end] = tmp;
      reverse(v, start+1, end-1); // Chamada recursiva para o resto
   }
   
   // -----------------------------------------------------------
    // Verifica se array é capicua
   static boolean capicua(int v[], int start, int end) {
      if (start >= end) return true;          // Caso base: 0 ou 1 elemento
      if (v[start] != v[end]) return false;   // Se primeiros e últimos são diferentes
      return capicua(v, start+1, end-1);      // Recursão para o intervalo interno
   }
   
    public static void main(String[] args) {
      int v1[] = {2,4,6,8,10}; 
      int v2[] = {1,2,3,2,1};
      int v3[] = {5,8,8,5};
      int v4[] = {1,2,3,1};
      int v5[] = {5,8,7,5};

      System.out.println("Antes do reverse: " + Arrays.toString(v1));
      reverse(v1, 0, v1.length-1);
      System.out.println("Depois do reverse: " + Arrays.toString(v1));

      System.out.println("capicua(v2) = " + capicua(v2, 0, v2.length-1)); // true
      System.out.println("capicua(v3) = " + capicua(v3, 0, v3.length-1)); // true
      System.out.println("capicua(v4) = " + capicua(v4, 0, v4.length-1)); // false
      System.out.println("capicua(v5) = " + capicua(v5, 0, v5.length-1)); // false
   }
}