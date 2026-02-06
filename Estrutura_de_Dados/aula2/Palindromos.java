/*O problema

Um palíndromo é uma palavra ou frase número cuja leitura é a mesma, quer se faça da esquerda para a direita. Num palíndromo consideramos apenas as letras e desconsideramos quaisquer outros caracteres (como por exemplo sinais de pontuação ou espaços). É também considerado que uma letra minúscula é igual à sua equivalente maiúscula (por exemplo 'a' é igual a 'A'.

A título de exemplo as seguintes linhas são todas palíndromos:

Madam, I'm Adam
reviver
ato idiota
O bolo do lobo
Socorram Marrocos
A base do teto desaba
Anotaram a data da maratona

A tua tarefa é descobrir quais as linhas do input que são palíndromos.
Input

A primeira linha de input contém um número N (1 ≤ N ≤ 100), que corresponde ao número de linhas a processar.

Seguem-se exactamente N linhas, contendo cada uma com um máximo de 100 caracteres, sendo que nenhuma das letras do input tem acentos.
Output

A primeira linha do output deve conter o número N. Devem seguir-se N linhas, contendo cada uma:

    sim, caso a linha correspondente no input seja um palíndromo
    nao, caso contrário */

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
