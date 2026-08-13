import java.util.Scanner;

public class Count {
   public static void main(String[] args) {
	
      Scanner stdin = new Scanner(System.in);
		
      int counter = 0;
      while (stdin.hasNextLine()) {        // agora verifica se há "tokens" disponíveis
         counter++;
         String s = stdin.nextLine();      // lê o próximo token (não a linha inteira)
         System.out.println(counter + ": " + s);
      }
   }
}
