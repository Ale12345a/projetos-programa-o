/*O Problema

Dados vários pares de inteiros Ni e Ki, a tua tarefa é descobrir, para cada par, qual o menor número maior que Ni tal que a soma dos seus dígitos seja exactamente Ki.
Input

Na primeira linha do input vem um inteiro T indicando o número de casos de teste, ou seja, o número de pares de inteiros a considerar.

Seguem-se T linhas, cada uma com os dois inteiros Ni Ki correspondentes.
Output

O output deve conter T linhas. A i-ésima linha deve conter um único inteiro Ri indicando a resposta para o par correspondente, ou seja, qual o número mais pequeno que é simultaneamente maior que Ni e com a soma dos seus dígitos a ser Ki.*/
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
