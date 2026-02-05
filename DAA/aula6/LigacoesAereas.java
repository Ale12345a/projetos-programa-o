import java.util.*;

public class LigacoesAereas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        int[][] mat = new int[N][N];

        // Diagonal principal = 1
        for (int i = 0; i < N; i++) mat[i][i] = 1;

        // Ler voos
        for (int i = 0; i < N; i++) {
            String linha = sc.nextLine().trim();
            String[] partes = linha.split(" ");
            // partes[0] é a letra da cidade (A, B, ...)
            int from = partes[0].charAt(0) - 'A';
            int qtd = Integer.parseInt(partes[1]);
            for (int j = 0; j < qtd; j++) {
                int to = partes[2 + j].charAt(0) - 'A';
                mat[from][to] = 1;
            }
        }

        // Floyd-Warshall para fechar transitivamente
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (mat[i][k] == 1) {
                    for (int j = 0; j < N; j++) {
                        if (mat[k][j] == 1) {
                            mat[i][j] = 1;
                        }
                    }
                }
            }
        }

        // Imprimir cabeçalho
        System.out.print("  ");
        for (int i = 0; i < N; i++) {
            System.out.print((char) ('A' + i));
            if (i < N - 1) System.out.print(" ");
        }
        System.out.println();

        // Imprimir matriz
        for (int i = 0; i < N; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < N; j++) {
                System.out.print(mat[i][j]);
                if (j < N - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}
