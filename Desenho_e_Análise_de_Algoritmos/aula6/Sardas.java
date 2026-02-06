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
