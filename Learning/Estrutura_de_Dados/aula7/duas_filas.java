// -----------------------------------------------------------
// Programa completo: duas_filas.java
// -----------------------------------------------------------

/*Este método deve processar os elementos que vêm na fila q na forma de um nome seguido de uma operação. Consoante a operação deve fazer o seguinte:

    Nome A - Adiciona nome à fila a
    Nome B - Adiciona nome à fila b
    Nome X - Adiciona nome à fila que tenha menos elementos (a ou b). Se ambas as filas tiverem o mesmo número de elementos, o nome é descartado e não é adicionada a nenhuma. 

Por exemplo, se a fila q fosse {Luis,B,Pedro,A,Luisa,A,Joao,X,Jose,X,Miguel,B} acontecia o seguinte:

    Luis B - Luis é adicionado à fila b
    Pedro A - Pedro é adicionado à fila a
    Luisa A - Luisa é adicionada à fila a
    Joao X - Joao é adicionado à fila b, que tem apenas 1 elemento (Luis) contra os dois da fila b (Pedro e Luisa)
    Jose X - José descartado (ambas as filas têm 2 elementos)
    Miguel B - Miguel é adicionado à fila b 

No final a fila a fica com {Pedro,Luisa} e a fila b fica com {Luis,Joao,Miguel}. A fila q deve ficar vazia. */

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
