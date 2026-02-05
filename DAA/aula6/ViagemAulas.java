import java.util.*;
import java.io.*;

public class ViagemAulas {
    static class Edge {
        int to;
        double w;
        Edge(int t, double w) { this.to = t; this.w = w; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // aceita números com ponto como decimal
        //Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int E = sc.nextInt();
        sc.nextLine();

        String startName = sc.next();
        String endName = sc.next();

        // mapear nomes para ids
        Map<String, Integer> map = new HashMap<>();
        int idCounter = 0;

        if (!map.containsKey(startName)) map.put(startName, idCounter++);
        if (!map.containsKey(endName)) map.put(endName, idCounter++);

        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            String a = sc.next();
            String b = sc.next();
            double w = sc.nextDouble();

            if (!map.containsKey(a)) map.put(a, idCounter++);
            if (!map.containsKey(b)) map.put(b, idCounter++);

            int u = map.get(a);
            int v = map.get(b);

            adj.get(u).add(new Edge(v, w));
            adj.get(v).add(new Edge(u, w));
        }

        int start = map.get(startName);
        int end = map.get(endName);

        double[] dist = dijkstra(N, adj, start);

        System.out.printf("%.1f\n", dist[end]);
    }

    static double[] dijkstra(int n, ArrayList<ArrayList<Edge>> adj, int src) {
        double[] dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[src] = 0.0;

        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));
        pq.add(new double[]{src, 0.0});

        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            int u = (int)cur[0];
            double d = cur[1];
            if (d > dist[u]) continue;

            for (Edge e : adj.get(u)) {
                int v = e.to;
                double nd = d + e.w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.add(new double[]{v, nd});
                }
            }
        }
        return dist;
    }
}
