import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        // Lê dois números decimais
        double a = stdin.nextDouble();
        double b = stdin.nextDouble();

        // Imprime a soma
        System.out.println(a + b);

        stdin.close();
    }
}
