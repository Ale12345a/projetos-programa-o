/*O problema

Dada uma lista de palavras (possivelmente repetidas), a tua tarefa é descobrir quantas palavras diferentes existem.
Input

Na primeira linha do input vem um número N (1 ≤ N ≤ 1,000) que corresponde à quantidade de palavras a considerar.

Seguem-se N linhas, cada uma contendo uma palavra. AS palavras são constituídas unicamente por letras minúsculas e têm tamanho entre 1 e 20.
Output
O output deve ser constituído por um unico inteiro indicando quantas palavras diferentes existem.  */

import java.util.Scanner;

public class palavras {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        BSTree<String> tree = new BSTree<String>();

        for (int i = 0; i < N; i++) {
            String word = sc.next();
            tree.insert(word);
        }

        System.out.println(tree.numberNodes());
        sc.close();
    }
}