// -----------------------------------------------------------
// Programa completo: duas_filas.java
// Junta ED196 + main de teste
// -----------------------------------------------------------

public class duas_filas {

    // Método process
    public static void process(MyQueue<String> q, MyQueue<String> a, MyQueue<String> b) {
        while (!q.isEmpty()) {
            String nome = q.dequeue();
            String op = q.dequeue();

            if (op.equals("A")) {
                a.enqueue(nome);
            } 
            else if (op.equals("B")) {
                b.enqueue(nome);
            } 
            else if (op.equals("X")) {
                if (a.size() < b.size()) {
                    a.enqueue(nome);
                } 
                else if (b.size() < a.size()) {
                    b.enqueue(nome);
                }
                // se forem iguais, descarta
            }
        }
    }

    // Método main
    public static void main(String[] args) {
        MyQueue<String> q = new LinkedListQueue<String>();
        MyQueue<String> a = new LinkedListQueue<String>();
        MyQueue<String> b = new LinkedListQueue<String>();

        String[] dados = {"Luis","B","Pedro","A","Luisa","A","Joao","X","Jose","X","Miguel","B"};
        for (String s : dados) q.enqueue(s);

        process(q, a, b);

        System.out.println("q = " + q);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
