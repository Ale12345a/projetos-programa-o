import java.util.Scanner;

public class PlaylistOptima {

    static int D;       // duração máxima da viagem
    static int N;       // número de músicas
    static int[] dur;   // duração de cada música
    static int bestSum; // melhor soma encontrada

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        D = in.nextInt();
        N = in.nextInt();
        dur = new int[N];

        for (int i = 0; i < N; i++) {
            dur[i] = in.nextInt();
        }

        bestSum = 0;
        boolean[] used = new boolean[N];
        gerarSubconjuntos(0, 0, used);

        System.out.println(bestSum);
        in.close();
    }

    // cur = índice atual, sum = soma atual do subconjunto
    static void gerarSubconjuntos(int cur, int sum, boolean[] used) {
        if (sum > D) return; // descartamos subconjuntos que ultrapassam a duração

        if (cur == N) { // caso base: terminamos o subconjunto
            if (sum > bestSum) bestSum = sum; // atualiza melhor soma
        } else {
            // incluir a música atual
            gerarSubconjuntos(cur + 1, sum + dur[cur], used);
            // não incluir a música atual
            gerarSubconjuntos(cur + 1, sum, used);
        }
    }
}
