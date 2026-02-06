import java.util.*;

// Classe para nós da fila do BFS
class NodeQ {
    int node;
    int flow;

    NodeQ(int node, int flow) {
        this.node = node;
        this.flow = flow;
    }
}

// Classe do grafo
class Graph {
    int n;
    List<List<Integer>> adj;
    int[][] cap;

    Graph(int n) {
        this.n = n;
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            adj.add(new ArrayList<>());
        cap = new int[n + 1][n + 1];
    }

    void addLink(int a, int b, int c) {
        adj.get(a).add(b);
        adj.get(b).add(a);   // necessário para o grafo residual
        cap[a][b] += c;      // += permite múltiplas arestas
    }

    // BFS do Edmonds-Karp
    int bfs(int s, int t, int[] parent) {
        Arrays.fill(parent, -1);
        parent[s] = -2;

        Queue<NodeQ> q = new LinkedList<>();
        q.add(new NodeQ(s, Integer.MAX_VALUE));

        while (!q.isEmpty()) {
            NodeQ curQ = q.poll();
            int cur = curQ.node;
            int flow = curQ.flow;

            for (int next : adj.get(cur)) {
                if (parent[next] == -1 && cap[cur][next] > 0) {
                    parent[next] = cur;
                    int newFlow = Math.min(flow, cap[cur][next]);

                    if (next == t)
                        return newFlow;

                    q.add(new NodeQ(next, newFlow));
                }
            }
        }
        return 0;
    }

    int maxFlow(int s, int t) {
        int flow = 0;
        int[] parent = new int[n + 1];

        while (true) {
            int newFlow = bfs(s, t, parent);
            if (newFlow == 0) break;

            flow += newFlow;

            int cur = t;
            System.out.print("Caminho de aumento (fluxo " + newFlow + "): " + t);
            while (cur != s) {
                int prev = parent[cur];
                cap[prev][cur] -= newFlow;
                cap[cur][prev] += newFlow;
                cur = prev;
                System.out.print(" <- " + cur);
            }
            System.out.println();
        }
        return flow;
    }
}

public class edmondskarp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Ler número de nós e arestas
        int n = in.nextInt();
        int e = in.nextInt();

        Graph g = new Graph(n);

        // Ler arestas
        for (int i = 0; i < e-1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            g.addLink(a, b, c);
        }

        // Ler origem e destino
        int s = in.nextInt();
        int t = in.nextInt();

        int flow = g.maxFlow(s, t);
        System.out.println("Fluxo máximo: " + flow);

        in.close();
    }
}
