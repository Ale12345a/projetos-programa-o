import java.util.Scanner;

public class Losango {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int N = stdin.nextInt(); // tamanho da linha central
        int mid = N / 2;        // índice da linha central (0-based)

        for (int i = 0; i < N; i++) {
            // calcular número de # na linha atual
            int numHashes = N - 2 * Math.abs(mid - i);
            int numDots = (N - numHashes) / 2;

            // imprimir pontos à esquerda
            for (int j = 0; j < numDots; j++)
                System.out.print('.');
            // imprimir cardinais
            for (int j = 0; j < numHashes; j++)
                System.out.print('#');
            // imprimir pontos à direita
            for (int j = 0; j < numDots; j++)
                System.out.print('.');
            // nova linha
            System.out.println();
        
    }
}
}