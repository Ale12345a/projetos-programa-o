import java.util.*;
import java.io.*;

public class Fibra {

    static class Edge implements Comparable<Edge> {
        int u, v, cost;
        Edge(int u, int v, int cost) { this.u = u; this.v = v; this.cost = cost; }
        public int compareTo(Edge e) { return Integer.compare(this.cost, e.cost); }
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
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        parent = new int[A + B + 1];
        for (int i = 1; i <= A + B; i++) parent[i] = i;

        // Conectar todas as casas existentes inicialmente
        for (int i = 2; i <= A; i++) union(1, i);

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < C; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int p = sc.nextInt();
            edges.add(new Edge(x, y, p));
        }

        Collections.sort(edges);

        int count = 0;
        long totalCost = 0;
        List<Integer> usedCosts = new ArrayList<>();

        for (Edge e : edges) {
            if (union(e.u, e.v)) {
                totalCost += e.cost;
                // Apenas armazena as arestas que conectam novas casas ao componente
                if (e.u > A || e.v > A) {
                    usedCosts.add(e.cost);
                    count++;
                    if (count == B) break; // Todas as novas casas conectadas
                }
            }
        }

        Collections.sort(usedCosts);

        System.out.println(totalCost);
        for (int i = 0; i < usedCosts.size(); i++) {
            System.out.print(usedCosts.get(i));
            if (i < usedCosts.size() - 1) System.out.print(" ");
        }
        System.out.println();
    }
}
