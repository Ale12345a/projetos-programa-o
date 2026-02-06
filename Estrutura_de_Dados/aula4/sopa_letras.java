/*O problema

Todos conhecem o puzzle da "Sopa de Letras", que consiste basicamente em encontrar palavras, num emaranhado de letras.

Uma sopa de letras é basicamente um quadriculado de letras, sempre maiúsculas, e uma palavra pode ser encontrada apenas na horizontal (da direita para a esquerda ou da esquerda para a direita) ou na vertical (de cima para baixo, ou de baixo para cima). Para este problema, as palavras não podem vir na diagonal.

A tua tarefa é resolver uma sopa de letras.

Por exemplo, imagina que tinhas de procurar as seguintes palavras na sopa de letras da figura:

SOPA
LETRA
EDA

A ideia é que o teu programa descubra onde estão as palavras. Para que mostres que realmente as descobriste, todas as letras que não pertencem a palavras devem ser substituidas por um ponto, como exemplificado na figura. Consulta o exemplo de input e output para clarificares o que deves fazer.
Input

Um ficheiro de input contém vários casos de teste.

A primeira linha de teste contém dois números LINS COLS, indicando respectivamente o número de linhas e colunas da sopa de letras (1 ≤ LINS,COLS ≤ 100).

Seguem-se LINS linhas de input, cada uma contendo COLS letras maiúsculas, sem espaços a separá-las.

De seguida vem N (1 ≤ N ≤ 50), o número de palavras a pesquisar, sendo que as N linhas seguintes contêm precisamente as palavras, também apenas representadas por letras maiúsculas (cada palavra tem o tamanho máximo de 50 letras). É garantido que todas estas palavras aparecem no input apenas uma só vez (numa das quatro direcções válidas)

Um caso com 0 linhas e 0 colunas sinaliza o final do input, e não deve ser processado.
Output

Para cada caso deve começar por ser imprimida uma linha de output, "Input #NUM", onde NUM representa o número do caso, seguido da impressão da sopa de letras, no formato pedido, com as palavras destacadas.

Vê o exemplo para clarificar a maneira como deve ser feito o output. */

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
