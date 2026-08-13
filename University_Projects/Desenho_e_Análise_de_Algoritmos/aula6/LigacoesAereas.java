/*O Problema

A tua tarefa é ajudá-los, fazendo um programa que descubra que cidades estão ligadas por voos, quer directamente, quer indirectamente, através de outros voos, passando por cidades intermédias.
Input

A primeira linha contém um único inteiro N, o número de cidades a considerar. Cada cidade é representada por uma letra maiúscula, começando a partir do A. Por exemplo, se tivermos 4 cidades, serão representadas por A, B, C e D.

Seguem-se N linhas, cada uma indicando os voos directos entre a cidade correspondente e outras cidades, no formato: cidade-origem num_destinos destino_1 destino_2 ... destino_m.

Existe uma linha para cada cidade (mesmo que não tenha voos) e as cidades-origem vêm sempre por ordem alfabética. Nota que o facto de existir um voo de A para B não significa que exista um voo de B para A.
Output

Deve ser imprimida uma matriz, de N por N, indicando todas as cidades que estão ligadas, no formato:

  A B C ...
A 1 0 0 ...
B 1 1 1 ...
C 0 0 1 ...
. . . . ...
. . . . ...
. . . . ...

0 (zero) significa que não existe maneira de chegar de uma cidade a outra e 1 (um) precisamente o contrário, ou seja, que existe maneira (directa ou indirecta) de o fazer.

Podes assumir que na diagonal principal vêm sempre uns, ou seja, é sempre possível viajar de uma cidade para ela própria (basta não sair do sitio...)

Nota que não devem existir espaços a mais no final de cada linha do input (existe um único espaço a separar cada letra ou número, excepto no caso da primeira linha, onde o A é precedido de dois espaços, de modo a que a tabela fique alinhada). */

import java.util.*;

public class LigacoesAereas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        int[][] mat = new int[N][N];

        // Diagonal principal = 1
        for (int i = 0; i < N; i++) mat[i][i] = 1;

        // Ler voos
        for (int i = 0; i < N; i++) {
            String linha = sc.nextLine().trim();
            String[] partes = linha.split(" ");
            // partes[0] é a letra da cidade (A, B, ...)
            int from = partes[0].charAt(0) - 'A';
            int qtd = Integer.parseInt(partes[1]);
            for (int j = 0; j < qtd; j++) {
                int to = partes[2 + j].charAt(0) - 'A';
                mat[from][to] = 1;
            }
        }

        // Floyd-Warshall para fechar transitivamente
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (mat[i][k] == 1) {
                    for (int j = 0; j < N; j++) {
                        if (mat[k][j] == 1) {
                            mat[i][j] = 1;
                        }
                    }
                }
            }
        }

        // Imprimir cabeçalho
        System.out.print("  ");
        for (int i = 0; i < N; i++) {
            System.out.print((char) ('A' + i));
            if (i < N - 1) System.out.print(" ");
        }
        System.out.println();

        // Imprimir matriz
        for (int i = 0; i < N; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < N; j++) {
                System.out.print(mat[i][j]);
                if (j < N - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}
