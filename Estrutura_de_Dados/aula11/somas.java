/*O problema

Dada um conjunto de N números e um conjunto de P perguntas indicando cada uma um número Xi, a tua tarefa é descobrir, para cada pergunta, se o número Xi pode ser formado somando dois números (possivelmente iguais) do conjunto dado.

Imagine por exemplo que o conjunto é {2,6,8,10}:

    4 (2+2), 8 (2+6), 10 (2+8), 12 (2+10) e 16 (6+10) são exemplos de perguntas que teriam resposta afirmativa
    2, 3, 5, 15 ou 21 são exemplos de perguntas que teriam resposta negativa 

Input

Na primeira linha do input vem um número N (1 ≤ N ≤ 1,000) que corresponde à quantidade de números do conjunto. Segue-se uma linha com N inteiros positivos (menores que um milhão), separados por um espaço, indicando os números do conjunto.

Na terceira linha do input vem um número P (1 ≤ N ≤ 5,000) que corresponde à quantidade de perguntas. Segue-se uma linha com P inteiros positivos (menores que um milhão), separados por um espaço, indicando as perguntas Xi.
Output
O output deve ser constituído por P linhas, uma por cada pergunta, no formato Xi: sim se o Xi puder ser formado como soma de dois números do conjunto dado, ou Xi: nao caso contrário.  */

import java.util.Scanner;

public class somas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        BSTree<Integer> tree = new BSTree<>();

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
            tree.insert(nums[i]);
        }

        int P = sc.nextInt();
        int[] queries = new int[P];
        for (int i = 0; i < P; i++) {
            queries[i] = sc.nextInt();
        }

        for (int i = 0; i < P; i++) {
            int X = queries[i];
            boolean found = false;

            for (int j = 0; j < N; j++) {
                int a = nums[j];
                int b = X - a;
                if (tree.contains(b)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println(X + ": sim");
            } else {
                System.out.println(X + ": nao");
            }
        }

        sc.close();
    }
}
