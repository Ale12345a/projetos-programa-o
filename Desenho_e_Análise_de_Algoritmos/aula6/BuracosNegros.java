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
