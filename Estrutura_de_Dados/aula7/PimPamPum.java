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
