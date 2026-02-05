public class process {
    public static void process(MyQueue<String> q, MyQueue<String> a, MyQueue<String> b) {
        while (!q.isEmpty()) {
            String name = q.dequeue();      // retira o nome
            if (q.isEmpty()) break;         // caso de segurança, não há operação
            String op = q.dequeue();        // retira a operação
            
            if (op.equals("A")) {
                a.enqueue(name);
            } else if (op.equals("B")) {
                b.enqueue(name);
            } else if (op.equals("X")) {
                if (a.size() < b.size()) {
                    a.enqueue(name);
                } else if (b.size() < a.size()) {
                    b.enqueue(name);
                }
                // se forem iguais, descarta o nome
            }
        }
    }

    // Apenas para teste local
    public static void main(String[] args) {
        LinkedListQueue<String> q = new LinkedListQueue<>();
        LinkedListQueue<String> a = new LinkedListQueue<>();
        LinkedListQueue<String> b = new LinkedListQueue<>();
        
        String[] elems = {"Luis","B","Pedro","A","Luisa","A","Joao","X","Jose","X","Miguel","B"};
        for(String s : elems) q.enqueue(s);

        process(q, a, b);

        System.out.println("q = " + q); // q = {}
        System.out.println("a = " + a); // a = {Pedro,Luisa}
        System.out.println("b = " + b); // b = {Luis,Joao,Miguel}
    }
}
