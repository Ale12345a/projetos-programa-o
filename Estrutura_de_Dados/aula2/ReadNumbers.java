import java.util.Scanner;

public class ReadNumbers {

   // Escrever os numeros guardados num array no stdout
   static void writeArray(int v[]) {
      for (int i=0; i<v.length; i++)          
         System.out.println("v[" + i + "] = " + v[i]);      
   }

   // Função que calcula a amplitude do array (max - min)
    static int amplitude(int v[], int n) {
        if (n <= 0) return 0;  // array vazio ou inválido

        int min = v[0];
        int max = v[0];

        for (int i = 1; i < n; i++) {
            if (v[i] < min) min = v[i];
            if (v[i] > max) max = v[i];
        }
        return max - min;
    }
   
   public static void main(String[] args) {

      Scanner stdin = new Scanner(System.in);

      int v[] = new int[10];     // Cria um novo array com espaço para 10 inteiros           
      int n = stdin.nextInt();   // Ler a quantidade de numeros que se seguem

      // Garantir que n não excede o tamanho do array
      if (n > v.length) {
         System.out.println("Erro: número máximo de elementos é " + v.length);
         return;
      }
      
      for (int i=0; i<n; i++)    // Ler os numeros a partir do stdin
         v[i] = stdin.nextInt();
      
      writeArray(v);             // Chamar procedimento que escreve
      System.out.println("Amplitude = " + amplitude(v, n));  // calcular e mostrar amplitude
   }
}