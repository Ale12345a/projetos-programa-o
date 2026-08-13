/*O Problema

A tua tarefa é dizer à Sara qual a maneira de ligar todas as sardas de modo a minimizar a quantidade de tinta usada. A pequena Sara liga as sardas desenhando linhas rectas entre pares de sardas, possivelmente levantando a caneta entre linhas. Quando a Sara termina, deve existir uma sequência de linhas entre uma sarda e qualquer outra sarda.
Input

A primeira linha contém um único inteiro N, o número de sardas.

Seguem-se N linhas, cada uma com dois inteiros separados por um espaço indicando a posição (x,y) de uma sarda.
Output

Uma única linha com um número indicando a quantidade de tinta que a Sara deve gastar para ligar todas as sardas.

Para ser considerada correcta, a diferença entre número que escreveu e o número correto deve ser inferior ou igual a 0.01 (este problema tem um avaliador especial para verificar se isso acontece). Por exemplo, se a resposta correcta for "1.2345" qualquer uma das seguintes respostas seria aceite: "1.23", "1.24, "1.22", "1.234" ou "1.235" (já "1.3" não seria aceite, pois |1.2345 - 1.3| > 0.01). */

import java.util.*;
import java.io.*;

public class Sardas {

    static class Edge implements Comparable<Edge> {
        int u, v;
        double w;
        Edge(int u, int v, double w) { this.u = u; this.v = v; this.w = w; }
        public int compareTo(Edge e) { return Double.compare(this.w, e.w); }
    }

    static int[] parent;
    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    static boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return false;
        parent[pa] = pb;
        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        double[][] points = new double[N][2];
        for (int i = 0; i < N; i++) {
            points[i][0] = sc.nextDouble();
            points[i][1] = sc.nextDouble();
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double dx = points[i][0] - points[j][0];
                double dy = points[i][1] - points[j][1];
                edges.add(new Edge(i, j, Math.sqrt(dx*dx + dy*dy)));
            }
        }

        Collections.sort(edges);

        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        double total = 0.0;
        int count = 0;
        for (Edge e : edges) {
            if (union(e.u, e.v)) {
                total += e.w;
                count++;
                if (count == N - 1) break; // MST completa
            }
        }

        System.out.printf("%.5f\n", total);
    }
}
