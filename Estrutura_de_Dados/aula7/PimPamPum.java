/*O problema

"Pim, Pam, Pum, cada bola mata um, p'ra galinha e para o peru quem se livra és mesmo tu"

Quem não se lembra de ter feito este jogo quando era pequeno? A ideia é muito simples. A frase atrás descrita tem 19 palavras:

"Pim, Pam, Pum, cada bola mata um, p'ra galinha e para o peru quem se livra és mesmo tu"
  1    2     3    4   5    6   7    8      9    10 11  12 13   14  15  16   17  18   19

3 crianças resolveram jogar este jogo, o Paulo, a Raquel e o Carlos, que estão dispostas por esta ordem. O Paulo começa a dizer a frase, e em cada palavra vai apontando para as pessoas. Ao chegar ao fim da lista de pessoas, volta ao início, a ele mesmo, como exemplificado.


Figura 1 - Exemplo do jogo do "Pim, Pam, Pum" com 3 crianças.

Quem se livrava no início era o Paulo. Agora imagina que o jogo continuava. A pessoa que se livrou sai da lista, e a contagem recomeça na próxima pessoa (neste caso a Raquel). Quem se livrava a seguir era a Raquel e o Carlos, coitado, era o que não se livrava.

Descontente com isto, o Carlos, sabendo que és um bom programador, resolveu pedir-te ajuda. O que ele quer saber é, dada uma configuração inicial, se ele irá perder o jogo. O pior é que ele não sabe que frase vai ser usada. Por vezes e aquela frase comprida, outras vezes é uma mais pequena (como por exemplo, "Pim, Pam, Pum, quem se livra és tu", que neste caso teria 7 palavras). Podes ajudá-lo?
Input

A primeira linha contém um número N indicando o número de casos a analisar-

De seguida vêm N casos, cada um definido por duas linhas:

    Na primeira vez a frase que vai ser usada para a contagem (com não mais do que 50 palavras)
    Na segunda vem um número K (0<K<100), indicando o número de crianças que vão jogar o jogo, logo seguido de K nomes, indicando o nome das crianças. 

Podes assumir que um dos nomes (que são sempre formados por uma única palavra) será sempre "Carlos" e que a frase será apenas constituída por letras (maísculas ou minúsculas, mas sem acentos) e espaços (para efeitos de contagem, uma palavra é uma sequência de caracteres delimitada por espaços ou mudanças de linha).
Output
Para cada caso, vem uma linha de output:

    "O Carlos nao se livrou", indicando que o Carlos perde o jogo
    "O Carlos livrou-se (coitado do NomeX!)", onde NomeX indica a pessoa que perde o jogo.  */

import java.util.Scanner;

public class PimPamPum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        
        for (int caso = 0; caso < N; caso++) {
            // Lê a frase e conta o número de palavras
            String frase = sc.nextLine();
            String[] palavras = frase.trim().split("\\s+");
            int numPalavras = palavras.length;
            
            // Lê o número de crianças e seus nomes
            String[] linha = sc.nextLine().split("\\s+");
            int K = Integer.parseInt(linha[0]);
            CircularLinkedList<String> lista = new CircularLinkedList<>();
            for (int i = 1; i <= K; i++) {
                lista.addLast(linha[i]);
            }
            
            // Rodada do jogo
            while (lista.size() > 1) {
                // Girar lista numPalavras - 1 vezes
                for (int i = 0; i < numPalavras - 1; i++) {
                    lista.rotate();
                }
                
                String eliminado = lista.getFirst(); // Quem perde agora
                lista.removeFirst();
                
                // Se a lista tiver sobrado só com Carlos, termina
                if (lista.size() == 1) break;
            }
            
            String sobrevivente = lista.getFirst();
            
            if (sobrevivente.equals("Carlos")) {
                System.out.println("O Carlos nao se livrou");
            } else {
                System.out.println("O Carlos livrou-se (coitado do " + sobrevivente + "!)");
            }
        }
        
        sc.close();
    }
}
