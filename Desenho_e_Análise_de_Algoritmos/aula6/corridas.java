/*O Problema

Dado o mapa do parque (o grafo), tens de ajudar o Aniceto a descobrir qual o maior número de caminhos diferentes (sem partilhar arestas) que se consegue formar entre o primeiro e o último nó.
Input

A primeira linha contém dois inteiros V e E indicando respectivamente o número de nós e de arestas (trilhos) a considerar.

Seguem-se exactamente E linhas, cada uma com dois inteiros A B, indicando que existe um trilho (bidirecional) entre os nós A e B.

Os nós são representados por inteiros entre 1 e V (inclusive) e é garantido que existe sempre pelo menos um caminho entre 1 e V.
Output

O output deve ser constituído por uma única linha indicando o número máximo de caminhos diferentes (sem partilhar trilhos) que podem existir entre o nó 1 e o nó V.  */

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
