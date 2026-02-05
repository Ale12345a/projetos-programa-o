//DAA029

import java.util.*;

public class OrdemRara {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        String[] words = new String[N];
        for (int i = 0; i < N; i++) words[i] = sc.nextLine();

        // 1. Coletar todas as letras
        Set<Character> allChars = new HashSet<>();
        for (String w : words)
            for (char c : w.toCharArray())
                allChars.add(c);

        // 2. Construir grafo (adjacency list) e grau de entrada
        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> indeg = new HashMap<>();
        for (char c : allChars) {
            adj.put(c, new HashSet<>());
            indeg.put(c, 0);
        }

        // 3. Comparar palavras consecutivas
        for (int i = 0; i < N-1; i++) {
            String w1 = words[i], w2 = words[i+1];
            int min = Math.min(w1.length(), w2.length());
            for (int j = 0; j < min; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    char a = w1.charAt(j), b = w2.charAt(j);
                    if (!adj.get(a).contains(b)) {
                        adj.get(a).add(b);
                        indeg.put(b, indeg.get(b)+1);
                    }
                    break;
                }
            }
        }

        // 4. Ordenamento topológico (Kahn)
        Queue<Character> q = new LinkedList<>();
        for (char c : indeg.keySet())
            if (indeg.get(c) == 0)
                q.add(c);

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char u = q.poll();
            sb.append(u);
            for (char v : adj.get(u)) {
                indeg.put(v, indeg.get(v)-1);
                if (indeg.get(v) == 0) q.add(v);
            }
        }

        System.out.println(sb.toString());
    }
}
