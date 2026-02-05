import java.util.Scanner;

public class Palindromos {
    
    // Função que verifica se uma linha é palíndromo
    static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        // Filtrar apenas letras e colocar em minúsculas
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        String filtered = sb.toString();
        String reversed = sb.reverse().toString();
        return filtered.equals(reversed);
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int N = stdin.nextInt();
        stdin.nextLine(); // consumir a quebra de linha após o número

        System.out.println(N);
        for (int i = 0; i < N; i++) {
            String line = stdin.nextLine();
            if (isPalindrome(line)) {
                System.out.println("sim");
            } else {
                System.out.println("nao");
            }
        }
    }
}
