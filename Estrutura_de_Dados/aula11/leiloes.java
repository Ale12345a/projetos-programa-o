import java.util.*;

public class leiloes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine(); // consumir o resto da linha

        // Fila de prioridade com preço decrescente
        PriorityQueue<Offer> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b.price, a.price)
        );

        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            
            if (parts[0].equals("OFERTA")) {
                String name = parts[1];
                int price = Integer.parseInt(parts[2]);
                pq.add(new Offer(name, price));
            } else if (parts[0].equals("VENDA")) {
                // Retira a oferta com preço máximo
                Offer top = pq.poll();
                System.out.println(top.price + " " + top.name);
            }
        }
    }

    // Classe auxiliar para guardar nome e preço
    static class Offer {
        String name;
        int price;
        Offer(String n, int p) {
            name = n;
            price = p;
        }
    }
}