/*O problema

Dado um conjunto de valores, a tua tarefa é calcular a sua média (soma a dividir pela quantidade de valores) e a sua amplitude (diferença entre o valor máximo e o valor mínimo).
Input

O input é constituído por duas linhas. A primeira linha contém um único número N (2 ≤ N ≤ 1 000), que corresponde à quantidade de números a considerar. A segunda linha contém os N valores a considerar, separados por um espaço. Os valores são inteiros entre -1 000 000 e 1 000 000 (inclusive).
Output

O output deve ser constituído por duas linhas. A primeira linha deve conter a média (com duas casas decimais) e a segunda linha deve conter a amplitude (um inteiro).  */

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
