import java.util.Scanner;

public class TSP {

    static int N;            // número de locais
    static String[] nomes;   // nomes dos locais
    static double[][] dist;  // matriz de distâncias
    static boolean[] used;   // marcas para permutação
    static int[] perm;       // permutação atual
    static double bestDist;  // menor distância encontrada

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        N = in.nextInt();
        nomes = new String[N];
        for (int i = 0; i < N; i++) {
            nomes[i] = in.next();
        }
        
        in.nextLine(); // consumir a linha restante

        dist = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = in.nextDouble();
            }
        }

        used = new boolean[N];
        perm = new int[N - 1]; // permutamos apenas os locais 1..N-1, 0 é fixo
        bestDist = Double.MAX_VALUE;

        gerarPerm(0);

        System.out.printf("%.2f\n", bestDist);
        in.close();
    }

    // cur = índice na permutação (apenas para locais 1..N-1)
    static void gerarPerm(int cur) {
        if (cur == N - 1) { // permutação completa
            double d = dist[0][perm[0]]; // S. Bento -> primeiro local
            for (int i = 0; i < N - 2; i++) {
                d += dist[perm[i]][perm[i + 1]]; // percurso entre locais
            }
            d += dist[perm[N - 2]][0]; // último local -> S. Bento
            if (d < bestDist) bestDist = d;
        } else {
            for (int i = 1; i < N; i++) { // tentar todos os locais exceto 0
                if (!used[i]) {
                    used[i] = true;
                    perm[cur] = i;
                    gerarPerm(cur + 1);
                    used[i] = false;
                }
            }
        }
    }
}
