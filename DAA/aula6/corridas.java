import java.util.*;

public class corridas {

    static int V, E;
    static ArrayList<Integer>[] adj;
    static boolean[][] used;

    static int maxPaths(int s, int t) {
        int count = 1;
        while (true) {
            boolean[] visited = new boolean[V + 1];
            if (!dfs(s, t, visited)) break;
            count++;
        }
        return count;
    }

    static boolean dfs(int u, int t, boolean[] visited) {
        if (u == t) return true;
        visited[u] = true;
        for (int i = 0; i < adj[u].size(); i++) {
            int v = adj[u].get(i);
            if (!used[u][v] && !visited[v]) {
                used[u][v] = used[v][u] = true; // marcar como usado
                if (dfs(v, t, visited)) return true;
                // se não levar ao sink, desfazemos o uso
                used[u][v] = used[v][u] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();

        adj = new ArrayList[V + 1];
        used = new boolean[V + 1][V + 1];
        for (int i = 1; i <= V; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        System.out.println(maxPaths(1, V));
    }
}
