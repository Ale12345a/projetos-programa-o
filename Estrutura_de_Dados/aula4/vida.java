/* -----------------------------------
  Estruturas de Dados 2018/2019
  Jogo da Vida [ED088]  
----------------------------------- */

/*O problema

O Jogo da Vida (Game of Life ou Life) é um autómato celular proposto em 1970 pelo matemático britânico John Horton Connway. Na realidade, trata-se de uma simulação e não de um jogo com jogadores. Desenvolve-se numa grelha infinita, na qual cada célula pode estar ocupada por um organismo ou não. Células ocupadas dizem-se que estão vivas e as não ocupadas dizem-se mortas. Em cada geração as células que estão vivas mudam o seu estado em função do número de células vizinhas que estão vivas de acordo com as seguintes regras:

    Os vizinhos de uma célula são as 8 células que a tocam na horizontal, vertical ou diagonal.
    Se uma célula estiver viva e o número de células vizinhas vivas for 0 ou 1, então na geração seguinte a célula morre de solidão.
    Se uma célula está viva e tem 4 ou mais células vizinhas também vivas, então na geração seguinte a célula morre de sobre-povoamento.
    Uma célula viva com 2 ou 3 células vizinhas vivas, continua viva na geração seguinte.
    Se uma célula morta tiver exactamente 3 células vizinhas vivas, muda para o estado de célula viva na geração seguinte.
    Todos os nascimentos e mortes de células têm lugar em simultâneo, isto é as alterações têm por base o estado actual e nunca o estado futuro.

As figuras seguintes ilustram dois estados consecutivos de uma configuração do jogo da vida.

Neste problema vamos considerar que a grelha é finita e que as células que a envolvem estão sempre mortas.
Input

A primeira linha contém três inteiros positivos: o número de linhas L e de colunas C que determinam a dimensão da grelha do jogo e o número de iterações I que deverá realizar.

Seguem-se L linhas de caracteres a representar o estado actual do jogo. As células mortas estão representadas por '.' e as vivas por 'O'.
Output

Deve imprimir o estado do jogo ao fim de I iterações, isto é, imprimir a matriz que representa a grelha do jogo com as células mortas marcadas com '.' e as células vivas com 'O'. */

import java.util.Scanner;

// Classe para representar um jogo
class Game {
    final char DEAD  = '.';  // Constante que indica uma celula morta
    final char ALIVE = 'O';  // Constante que indica uma celula viva
    private int rows, cols;  // Numero de linhas e colunas
    private char m[][];      // Matriz para representar o estado do jogo

    // Construtor: inicializa as variaveis tendo em conta a dimensao dada
    Game(int r, int c) {
        rows = r;
        cols = c;
        m = new char[r][c];
    }

    // Metodo para ler o estado inicial para a matriz m[][]
    public void read(Scanner in) {
        for (int i = 0; i < rows; i++)
            m[i] = in.next().toCharArray();
    }
    
    // Metodo para escrever a matriz m[][]
    public void write() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(m[i][j]);
            }
            System.out.println();
        }
    }

    // Deve devolver o numero de celulas vivas que sao vizinhas de (y,x)
    private int countAlive(int y, int x) {
        int count = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dy == 0 && dx == 0) continue; // não contar a própria célula
                int ny = y + dy;
                int nx = x + dx;
                if (ny >= 0 && ny < rows && nx >= 0 && nx < cols) {
                    if (m[ny][nx] == ALIVE) count++;
                }
            }
        }
        return count;
    }

    // Deve fazer uma iteracao: cria nova geracao a partir da actual
    public void iterate() {
        char[][] m2 = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int alive = countAlive(i, j);
                if (m[i][j] == ALIVE) {
                    if (alive == 2 || alive == 3) m2[i][j] = ALIVE;
                    else m2[i][j] = DEAD;
                } else {
                    if (alive == 3) m2[i][j] = ALIVE;
                    else m2[i][j] = DEAD;
                }
            }
        }
        // copiar m2 para m
        m = m2;
    }
}

// Classe principal com o main()
public class vida {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Ler linhas, colunas e numero de iteracoes
        int rows = in.nextInt();
        int cols = in.nextInt();
        int n    = in.nextInt();

        // Criar objecto para conter o jogo e ler estado inicial
        Game g = new Game(rows, cols);
        g.read(in);

        // Executar n iterações
        for (int i = 0; i < n; i++) {
            g.iterate();
        }

        // Escrever resultado final
        g.write();
    }
}
