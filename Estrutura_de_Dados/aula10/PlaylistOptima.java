/*O Problema

Dada a duração de uma viagem e um conjunto de músicas (especificadas pela sua duração), tens de calcular qual a duração da playlist ótima, ou seja qual o subconjunto de músicas tal que a soma das suas durações seja a maior possível e ao mesmo tempo essa soma seja menor ou igual à duração da viagem.
Input

Na primeira linha do input vem dois números inteiros D (1 ≤ D ≤ 10 000) e N (1 ≤ N ≤ 20) indicando a duração da viagem e o número de músicas disponíveis.

Seguem-se exactamente N linhas, cada uma com um número Mi indicando a duração da i-ésima música (as durações das músicas são inteiros entre 1 e 10 000). É garantido que pelo menos uma música tem duração igual ou inferior a D.
Output

O output é constituído por uma linha contendo um único número indicando a duração total da melhor playlist possível, ou seja, a escolha de um subconjunto de músicas que maximize ∑Mi tal que ∑Mi≤D  */

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
