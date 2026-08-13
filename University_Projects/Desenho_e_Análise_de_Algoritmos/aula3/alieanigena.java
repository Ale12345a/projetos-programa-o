/*O Problema

Dado um fragmento de ADN alienígena, a tua tarefa é produzir uma listagem das letras que aparecem em pelo menos uma vez, ordenada por ordem decrescente da sua frequência (número de occorrências) e em caso de empate pela ordem em que aparecem primeiro no fragmento (ou seja, tendo em conta a primeira ocorrência de cada letra).
Input

O input contém uma única linha contendo o fragmento a considerar. Este fragmento tem tamanho T e é constituído apenas por letras maiúsculas.
Output

O output deve listar todas as letras que aparecem pelo menos uma vez. Deve aparecer uma letra por linha, no formato "LETRA FREQUENCIA", onde FREQUENCIA é um inteiro indicando o número total de ocorrências dessa letra. As letras devem vir por ordem decrescente de frequência e em caso de empate pela ordem em que aparecem primeiro no input.  */

import java.util.Scanner;
import java.util.Arrays;

class Letter implements Comparable <Letter>{
	static int order=0;
	char letter;
	int rep=0,obj_order;
	Letter(char c){
		letter = c;
	}

	public void rep_count(){
		if(rep == 0){
			++order;
			obj_order=order;
		}
		++rep;
	}
	public int compareTo(Letter a){
		if(rep == a.rep){
			return a.obj_order - obj_order;
		}
		return rep-a.rep;
	}
}

public class alieanigena{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String letter_String= in.next();
		char c;

		Letter[] letter_array;
		letter_array = new Letter[26];

		for(int i=0; i<26; i++){
			letter_array[i] = new Letter((char)(65+i));
		}

		for(int i=0;i<letter_String.length();i++){
			c = letter_String.charAt(i);
			letter_array[(int)c-'A'].rep_count();
		}
		Arrays.sort(letter_array);

		for(int j = 25;j>=0;j--){
			if(letter_array[j].rep != 0){
				System.out.println(letter_array[j].letter + " " + letter_array[j].rep);
			}
		}
	}

}

