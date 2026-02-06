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

