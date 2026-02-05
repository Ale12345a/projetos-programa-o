import java.util.*;

public class sopa_letras {
    static int LINS, COLS;
    static char[][] grid;
    static boolean[][] marcado;

    // Verifica se uma palavra começa em (r,c) numa direção
    static boolean match(String word, int r, int c, int dr, int dc) {
        int len = word.length();
        for (int i = 0; i < len; i++) {
            int nr = r + dr * i;
            int nc = c + dc * i;
            if (nr < 0 || nr >= LINS || nc < 0 || nc >= COLS) return false;
            if (grid[nr][nc] != word.charAt(i)) return false;
        }
        // Se encontrou, marca as posições
        for (int i = 0; i < len; i++) {
            int nr = r + dr * i;
            int nc = c + dc * i;
            marcado[nr][nc] = true;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caso = 1;

        while (true) {
            LINS = in.nextInt();
            COLS = in.nextInt();
            if (LINS == 0 && COLS == 0) break;

            grid = new char[LINS][COLS];
            marcado = new boolean[LINS][COLS];

            for (int i = 0; i < LINS; i++) {
                grid[i] = in.next().toCharArray();
            }

            int N = in.nextInt();
            String[] palavras = new String[N];
            for (int i = 0; i < N; i++) {
                palavras[i] = in.next();
            }

            // Procura palavras
            for (String word : palavras) {
                boolean found = false;
                for (int r = 0; r < LINS && !found; r++) {
                    for (int c = 0; c < COLS && !found; c++) {
                        // 4 direções
                        found = match(word, r, c, 0, 1)   // direita
                             || match(word, r, c, 0, -1)  // esquerda
                             || match(word, r, c, 1, 0)   // baixo
                             || match(word, r, c, -1, 0); // cima
                    }
                }
            }

            // Imprime resultado
            System.out.println("Input #" + caso++);
            for (int r = 0; r < LINS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (marcado[r][c]) System.out.print(grid[r][c]);
                    else System.out.print('.');
                }
                System.out.println();
            }
        }
    }
}
