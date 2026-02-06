import java.util.*;

public class ClubeLivros {
    static int N;
    static List<Integer>[] graph;
    static int[] match;
    static boolean[] visited;

    static boolean bpm(int u) {
        for (int v : graph[u]) {
            if (!visited[v]) {
                visited[v] = true;
                if (match[v] == -1 || bpm(match[v])) {
                    match[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked") // 👈 aqui para remover o aviso do cast
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();
        while (C-- > 0) {
            N = sc.nextInt();
            int M = sc.nextInt();
            graph = (List<Integer>[]) new ArrayList[N]; // 👈 cast adicionado
            for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                graph[a].add(b);
            }

            match = new int[N];
            Arrays.fill(match, -1);
            int result = 0;
            for (int u = 0; u < N; u++) {
                visited = new boolean[N];
                if (bpm(u)) result++;
            }

            System.out.println(result == N ? "YES" : "NO");
        }
    }
}
