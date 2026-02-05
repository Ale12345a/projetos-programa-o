import java.util.*;

public class PlayList {

    static int best;              // melhor duração encontrada até agora
    static int D;                 // duração total da viagem
    static int music[];           // array com as durações das músicas

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        D = sc.nextInt();         // duração da viagem
        int N = sc.nextInt();     // número de músicas

        music = new int[N];
        for (int i = 0; i < N; i++) {
            music[i] = sc.nextInt();
        }

        best = 0;
        boolean used[] = new boolean[N];
        search(0, 0, used);       // começa a busca

        System.out.println(best);
    }

    // Função recursiva para explorar todos os subconjuntos
    static void search(int cur, int sum, boolean used[]) {
        if (cur == music.length) {
            // Chegamos ao fim do conjunto
            if (sum <= D && sum > best) {
                best = sum;       // Atualiza melhor valor
            }
        } else {
            // Opção 1: incluir a música atual se não ultrapassar D
            if (sum + music[cur] <= D) {
                used[cur] = true;
                search(cur + 1, sum + music[cur], used);
            }

            // Opção 2: não incluir a música atual
            used[cur] = false;
            search(cur + 1, sum, used);
        }
    }
}
