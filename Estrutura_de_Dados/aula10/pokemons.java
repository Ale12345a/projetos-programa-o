/*O Problema

Dado um conjunto de locais e a distância entre eles, tens de descobrir qual o tamanho do menor percurso que passa por todos os pontos voltando no final ao local inicial.
Input

Na primeira linha do input vem um número N (3 ≤ N ≤ 10) indicando o número de locais a considerar. Na segunda linha vêm exactamente N palavras indicando o nome dos locais. Cada nome contém apenas letras e tem um tamanho entre 1 e 30. O primeiro local indicado é o sítio onde o Aniceto está inicialmente.

Seguem-se exactamente N linhas, cada uma com N números, indicando a matriz de distâncias entre os locais. A j-ésima coluna da i-ésima linha representa a distância entre o local i e o local j (verifica o exemplo para perceberes melhor). Esta matriz é garantidamente simétrica, ou seja, distância(i,j)=distância(j,i).
Output

O output é constituído por uma linha contendo um único número indicando o comprimento do menor caminho que passa por todos os locais e volta ao local inicial, tal como atrás descrito. Este número deve vir arredondado a duas casas decimais.  */

import java.util.*;

public class pokemons {

    static int N;
    static String[] locais;
    static double[][] dist;
    static boolean[] used;
    static int[] perm;
    static double bestDist = Double.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        locais = new String[N];
        for (int i = 0; i < N; i++) locais[i] = sc.next();

        dist = new double[N][N];
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) 
                dist[i][j] = sc.nextDouble();

        used = new boolean[N];
        perm = new int[N - 1];

        // Marcamos o ponto inicial (0) como usado
        used[0] = true;

        // Gera permutações a partir do segundo local
        backtrack(0);

        // Imprime resultado com 2 casas decimais
        System.out.printf("%.2f\n", bestDist);
    }

    static void backtrack(int cur) {
        if (cur == N - 1) {
            // Calcula distância total desta permutação
            double total = 0.0;
            int prev = 0; // começa em SaoBento
            for (int i = 0; i < N - 1; i++) {
                total += dist[prev][perm[i]];
                prev = perm[i];
            }
            total += dist[prev][0]; // volta ao início

            if (total < bestDist) {
                bestDist = total;
            }
        } else {
            for (int i = 1; i < N; i++) { // começamos em 1 para não permutar SaoBento
                if (!used[i]) {
                    used[i] = true;
                    perm[cur] = i;
                    backtrack(cur + 1);
                    used[i] = false;
                }
            }
        }
    }
}
