/*Tarefa

Dada um grafo conexo não dirigido e não pesado descrevendo uma rede biológica, a tua tarefa é calcular o seu diâmetro, o seu raio, quais os nós centrais e quais os nós periféricos.
Input

Na primeira linha vem um único número inteiro N indicando o número de nós do grafo a considerar. Os nós são identificados por números inteiros consecutivos de 1 até N.

Na segunda linha vem um único número inteiro E indicando o número de arestas do grafo. Seguem-se E linhas, cada uma contendo dois inteiros A e B indicando que existe uma aresta (ligação direta) entre os nós A e B.

As arestas podem vir por qualquer ordem mas é garantido que nunca aparecem arestas repetidas. É também garantido que dão origem a um grafo completamente conexo.

O exemplo de input corresponde à figura dada anteriormente.
Output

Na primeira linha deve vir um único inteiro indicando o diâmetro da rede.

Na segunda linha deve vir um único inteiro indicando o raio.

Na terceira linha devem vir os nós centrais. Se existir mais que um nó central, deves escrevê-los por ordem crescente e com um espaço a separar cada par de nós.

Na quarta linha devem vir os nós periféricos. Se existir mais que um nó periférico, deves escrevê-los por ordem crescente e com um espaço a separar cada par de nós. */

import java.util.*;

/*
 Programa para calcular diâmetro, raio, centros e periféricos de um grafo conexo não ponderado.
 Entrada:
  N
  E
  A1 B1
  ...
 Saída:
  linha1: diâmetro
  linha2: raio
  linha3: nós centrais (ordenados, separados por espaço)
  linha4: nós periféricos (ordenados, separados por espaço)
*/
public class RedeBiologica {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int N = sc.nextInt();
        int E = sc.nextInt();

        ArrayList<Integer>[] adj = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adj[i] = new ArrayList<>();

        for (int i=0;i<E;i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        sc.close();

        int[] ecc = new int[N+1]; // excentricidades (1..N)

        // Para cada vértice fazer BFS e determinar sua excentricidade
        int[] dist = new int[N+1];
        int[] queue = new int[N+5];

        for (int s = 1; s <= N; s++) {
            Arrays.fill(dist, -1);
            int head = 0, tail = 0;
            queue[tail++] = s;
            dist[s] = 0;
            int maxd = 0;

            while (head < tail) {
                int u = queue[head++];
                for (int v : adj[u]) {
                    if (dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        queue[tail++] = v;
                        if (dist[v] > maxd) maxd = dist[v];
                    }
                }
            }
            // Como o grafo é garantidamente conexo, não precisamos verificar dist==-1
            ecc[s] = maxd;
        }

        int diameter = 0;
        int radius = Integer.MAX_VALUE;
        for (int i=1;i<=N;i++) {
            if (ecc[i] > diameter) diameter = ecc[i];
            if (ecc[i] < radius) radius = ecc[i];
        }

        StringBuilder centers = new StringBuilder();
        StringBuilder peripherals = new StringBuilder();
        boolean first;

        first = true;
        for (int i=1;i<=N;i++) {
            if (ecc[i] == radius) {
                if (!first) centers.append(' ');
                centers.append(i);
                first = false;
            }
        }

        first = true;
        for (int i=1;i<=N;i++) {
            if (ecc[i] == diameter) {
                if (!first) peripherals.append(' ');
                peripherals.append(i);
                first = false;
            }
        }

        System.out.println(diameter);
        System.out.println(radius);
        System.out.println(centers.toString());
        System.out.println(peripherals.toString());
    }
}
