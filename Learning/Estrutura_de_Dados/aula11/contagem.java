/*O problema

Dado uma texto, a tua tarefa é contar qual o número de ocorrências de cada palavra.
Input

Um texto num número arbitrário de linhas, sendo garantido que esse mesmo texto apenas contém letras minúsculas e espaços a separar palavras.
Output
O output deve ser constituído por uma linha por cada palavra que ocorre pelo menos uma vez no texto no formato "PALAVRA: NUMERO_OCORRENCIAS". As palavras devem vir por ordem alfabética.  */

import java.util.Scanner;
import java.util.LinkedList;

public class contagem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BSTMap<String,Integer> map = new BSTMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) continue; // ignorar linhas vazias
            String[] words = line.split(" ");
            for (String word : words) {
                Integer count = map.get(word);
                if (count == null) {
                    map.put(word, 1);
                } else {
                    map.put(word, count + 1);
                }
            }
        }

        LinkedList<String> keys = map.keys(); // já em ordem alfabética
        for (String key : keys) {
            System.out.println(key + ": " + map.get(key));
        }

        sc.close();
    }
}
