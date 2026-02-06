import java.util.*;

// Classe que representa um no
class Node {
    public LinkedList<Integer> adj;
    public boolean visited;
    public int distance;

    Node() {
        adj = new LinkedList<>();
    }
}

// Classe que representa um grafo
class Graph {
    int n;
    Node nodes[];

    Graph(int n) {
        this.n = n;
        nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++)
            nodes[i] = new Node();
    }

    public void addLink(int a, int b) {
        if (a < 1 || a > n || b < 1 || b > n) return;
        nodes[a].adj.add(b);
        nodes[b].adj.add(a);
    }

    // BFS
    public void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n; i++)
            nodes[i].visited = false;

        q.add(v);
        nodes[v].visited = true;
        nodes[v].distance = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.println(u + " [dist=" + nodes[u].distance + "]");

            for (int w : nodes[u].adj) {
                if (!nodes[w].visited) {
                    nodes[w].visited = true;
                    nodes[w].distance = nodes[u].distance + 1;
                    q.add(w);
                }
            }
        }
    }
}

public class BFS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int e = in.nextInt();

        Graph g = new Graph(n);

        for (int i = 0; i < e; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            g.addLink(a, b);
        }

        g.bfs(1);
        in.close();
    }
}
