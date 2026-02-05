import java.util.Scanner;

public class arranjadinhos{
    // Função que calcula a soma dos dígitos de n
    public static int sumDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            int num = N + 1;
            
            // Procura o primeiro número cuja soma dos dígitos seja K
            while (sumDigits(num) != K) {
                num++;
            }
            System.out.println();
            System.out.println(num);
        }

        sc.close();
    }
}
