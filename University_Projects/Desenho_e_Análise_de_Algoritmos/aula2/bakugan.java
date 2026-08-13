/*O Problema

Dada uma sequência de N bakugans, descrita pelas energias Ei de cada um deles, bem como uma série de F fotos, cada uma indicando que contém os bakugans entre as posições Ai e Bi, a tua tarefa é calcular, para cada foto, a soma das energias dos bakugans com posições no intervalo [Ai, Bi].
Input

Na primeira linha do input vem um inteiro N indicando o número de bakugans, seguida da uma linha contendo N inteiros Ei, indicando as energias da sequência de bakugans.

A terceira linha contém um inteiro F indicando o número de fotos. Seguem-se F linhas, cada uma contendo dois inteiros Ai e Bi indicando respetivamente a posição de início e de final da foto correspondente.
Output

O output deve conter F linhas. A i-ésima linha deve conter um único inteiro representando a soma de energia dos bakugans contidas nas posições entre Ai e Bi, inclusive. */

import java.util.Scanner;
import java.util.Arrays;

public class bakugan{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int [] power;
		power = new int[n];

		power[0] = in.nextInt();

		for(int i=1;i<n;i++){ // Preencher o array com o poder dos Bakugans
			power[i] = in.nextInt()+power[i-1];
		}

        System.out.println(Arrays.toString(power));
		
		int pics = in.nextInt();
		int num1, num2;
		int sum=0;

		for(int i=0;i<pics;i++){
			num1 = in.nextInt()-2;
			num2 = in.nextInt()-1;
			sum = power[num2];
			if(num1 >=0 && num1<n){
				sum -= power[num1];
			}
		System.out.println(sum);
		sum=0;
		}
		System.out.close();
	}
}