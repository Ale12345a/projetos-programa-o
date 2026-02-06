// Exemplo de codigo em Java
// [DAA 001] O sentido da vida
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 27/09/2020
// ----------------------------------

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
