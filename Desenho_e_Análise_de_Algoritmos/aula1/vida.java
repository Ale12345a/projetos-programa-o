// Exemplo de codigo em Java
// [DAA 001] O sentido da vida
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 27/09/2020
// ----------------------------------
/*O Problema

Dada uma sequência de N números inteiros, a tua tarefa é calcular a frequência do número 42, ou seja, quantas vezes ocorre o número 42 na sequência que te é dada.
Input

Na primeira linha do input vem um inteiro N indicando o tamanho da sequência, ou seja, a quantidade de números a considerar.

Seguem-se N linhas, cada uma com um inteiro Si indicando os números da sequência.
Output

O output é constituído por uma linha contendo um inteiro representando a quantidade de números Si da sequência que são exactamente 42. */
import java.util.Scanner;

public class vida {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	
	int n = in.nextInt();
	int count = 0;
	for (int i=0; i<n; i++) {
	    int s = in.nextInt();
	    if (s == 42) count++;
	}

	System.out.println(count);
    }
}
