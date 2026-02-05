import java.util.Scanner;

public class Estatisticas {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();         // lê quantidade de números
        int[] v = new int[n];

        // lê os números
        for (int i = 0; i < n; i++) {
            v[i] = stdin.nextInt();
        }

        // calcular soma, mínimo e máximo
        long sum = 0;  // usar long para evitar overflow
        int min = v[0];
        int max = v[0];

        for (int i = 0; i < n; i++) {
            sum += v[i];
            if (v[i] < min) min = v[i];
            if (v[i] > max) max = v[i];
        }

        // calcular média
        double mean = (double) sum / n;

        // calcular amplitude
        int amplitude = max - min;

        // imprimir resultados
        System.out.printf("%.2f\n", mean);
        System.out.println(amplitude);
    }
}
