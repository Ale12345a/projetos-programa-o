/*O Problema

Dada a duração de uma viagem e um conjunto de músicas (especificadas pela sua duração), tens de calcular qual a duração da playlist ótima, ou seja qual o subconjunto de músicas tal que a soma das suas durações seja a maior possível e ao mesmo tempo essa soma seja menor ou igual à duração da viagem.
Input

Na primeira linha do input vem dois números inteiros D (1 ≤ D ≤ 10 000) e N (1 ≤ N ≤ 20) indicando a duração da viagem e o número de músicas disponíveis.

Seguem-se exactamente N linhas, cada uma com um número Mi indicando a duração da i-ésima música (as durações das músicas são inteiros entre 1 e 10 000). É garantido que pelo menos uma música tem duração igual ou inferior a D.
Output

O output é constituído por uma linha contendo um único número indicando a duração total da melhor playlist possível, ou seja, a escolha de um subconjunto de músicas que maximize ∑Mi tal que ∑Mi≤D */

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
