/*O Problema

Um físico brilhante, que vive na Terra, quer usar os buracos negros para estudar a teoria do Big Bang. O cientista pretende ter uma sequência cíclica de buracos negros que o faça ir parar ao passado. Viajando nesta sequência muitas vezes, ele consegue ir parar tão para trás no tempo quanto o necessário, permitindo-lhe chegar ao início do universo e ver o Big Bang com os seus próprios olhos. Tens de o ajudar escrevendo um programa para ver se uma sequência como essa existe.
Input

A primeira linha contém um inteiro C indicando o número de casos a ser analisado.

Cada um dos casos de teste começa com uma linha contendo dois números inteiros N e M. N é o número de sistemas estelares, numerados de 0 a N-1. O sistema solar é sempre indicado pelo número zero. M é o número de buracos negros e a seguir a esta linha inicial de um caso seguem-se precisamente M linhas explicando como são os buracos negros. Cada uma destas linhas tem três inteiros no formato a b t, indicando um buraco negro que permite viajar do sistema a para o sistema b, chegando no tempo t em relação ao tempo de partida.
Output

O output deve conter exactamente C linhas, uma para cada caso, com a palavra "possivel" se for possível andar para trás no tempo indefinidamente (permitindo ao físico ver o Big Bang) ou a palavra "impossivel" se tal não for possível para os sistemas estelares e buracos negros dados. */

import java.util.*;

public class BuracosNegros {
    static class Edge {
        int from, to, weight;
        Edge(int f, int t, int w) {
            from = f; to = t; weight = w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt(); // número de casos

        for (int c = 0; c < C; c++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int t = sc.nextInt();
                edges.add(new Edge(a, b, t));
            }

            // Bellman-Ford
            long[] dist = new long[N];
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[0] = 0; // começa no sistema solar

            boolean updated = false;
            for (int i = 0; i < N - 1; i++) {
                updated = false;
                for (Edge e : edges) {
                    if (dist[e.from] != Long.MAX_VALUE &&
                        dist[e.from] + e.weight < dist[e.to]) {
                        dist[e.to] = dist[e.from] + e.weight;
                        updated = true;
                    }
                }
                if (!updated) break; // otimização
            }

            // ver se há ciclo negativo
            boolean cicloNegativo = false;
            for (Edge e : edges) {
                if (dist[e.from] != Long.MAX_VALUE &&
                    dist[e.from] + e.weight < dist[e.to]) {
                    cicloNegativo = true;
                    break;
                }
            }

            System.out.println(cicloNegativo ? "possivel" : "impossivel");
        }
    }
}
