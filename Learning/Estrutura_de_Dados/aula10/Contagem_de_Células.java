/*O Problema

Dado o estado de várias culturas de micróbios (indicados por uma matriz de células) a tua tarefa é descobrir qual o tamanho do maior micróbio em cada uma delas, ou seja, qual o tamanho do maior conjunto conexo de células em cada caso.
Input

Na primeira linha do input vem um número N indicando o número de casos a considerar (1 ≤ N ≤ 20). Cada dos casos começa com dois números L e C indicando respectivamente o número de linhas e colunas da caixa de petri a considerar (1 ≤ L, C ≤ 100), seguido de L linhas, cada uma com C caracteres, indicando o conteúdo de cada posição: '.' para uma posição vazia e '#' para uma posição com célula.
Output

O output deve ser constituido por N linhas, cada uma com o tamanho do maior micróbio do caso correspondente.  */

import java.util.Scanner;

public class Contagem_de_Células {

    static int rows;
    static int cols;
    static char m[][];
    static boolean visited[][];

    // Vizinhança 8-direções
    static int[] dy = {-1,-1,-1,0,0,1,1,1};
    static int[] dx = {-1,0,1,-1,1,-1,0,1};

    // Tamanho do micróbio que inclui posição (y,x)
    static int floodFill(int y, int x) {
        if (y < 0 || y >= rows || x < 0 || x >= cols) return 0; // Fora da matriz
        if (visited[y][x]) return 0;   // Já visitada
        if (m[y][x] == '.') return 0;  // Vazio

        visited[y][x] = true;
        int count = 1;

        for (int dir = 0; dir < 8; dir++) {
            count += floodFill(y + dy[dir], x + dx[dir]);
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();  // Número de casos

        for (int caso = 0; caso < N; caso++) {
            rows = in.nextInt();
            cols = in.nextInt();
            m = new char[rows][cols];
            visited = new boolean[rows][cols];

            for (int i = 0; i < rows; i++) {
                m[i] = in.next().toCharArray();
            }

            int maxSize = 0;

            // Iterar sobre todas as células
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (!visited[i][j] && m[i][j] == '#') {
                        int size = floodFill(i, j);
                        if (size > maxSize) maxSize = size;
                    }
                }
            }

            System.out.println(maxSize);
        }

        in.close();
    }
}
