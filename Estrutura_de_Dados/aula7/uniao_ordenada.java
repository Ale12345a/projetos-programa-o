// -----------------------------------------------------------
// Programa: ED197 - merge de duas filas ordenadas
// -----------------------------------------------------------

public class uniao_ordenada {

    public static MyQueue<Integer> merge(MyQueue<Integer> a, MyQueue<Integer> b) {
        MyQueue<Integer> result = new LinkedListQueue<Integer>();

        // Enquanto ambas as filas tiverem elementos
        while (!a.isEmpty() && !b.isEmpty()) {
            int va = a.first();
            int vb = b.first();

            if (va <= vb) {
                result.enqueue(a.dequeue());
            } else {
                result.enqueue(b.dequeue());
            }
        }

        // Se ainda restarem elementos na fila a
        while (!a.isEmpty()) {
            result.enqueue(a.dequeue());
        }

        // Se ainda restarem elementos na fila b
        while (!b.isEmpty()) {
            result.enqueue(b.dequeue());
        }

        return result;
    }

    // Método main para testar
    public static void main(String[] args) {
        MyQueue<Integer> a = new LinkedListQueue<Integer>();
        MyQueue<Integer> b = new LinkedListQueue<Integer>();

        int[] dadosA = {2, 4, 8, 10};
        int[] dadosB = {1, 4, 9};

        for (int x : dadosA) a.enqueue(x);
        for (int x : dadosB) b.enqueue(x);

        MyQueue<Integer> merged = merge(a, b);

        System.out.println("a = " + a); // deve ficar vazia
        System.out.println("b = " + b); // deve ficar vazia
        System.out.println("merged = " + merged);
    }
}
