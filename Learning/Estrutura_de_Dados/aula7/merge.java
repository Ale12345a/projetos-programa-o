public class merge {
    public static MyQueue<Integer> merge(MyQueue<Integer> a, MyQueue<Integer> b) {
        MyQueue<Integer> res = new LinkedListQueue<>();
        
        while (!a.isEmpty() && !b.isEmpty()) {
            if (a.first() <= b.first()) {
                res.enqueue(a.dequeue());
            } else {
                res.enqueue(b.dequeue());
            }
        }
        
        // Adiciona os restantes elementos da fila a ou b
        while (!a.isEmpty()) res.enqueue(a.dequeue());
        while (!b.isEmpty()) res.enqueue(b.dequeue());
        
        return res;
    }

    // Apenas para teste local
    public static void main(String[] args) {
        LinkedListQueue<Integer> a = new LinkedListQueue<>();
        LinkedListQueue<Integer> b = new LinkedListQueue<>();

        int[] arrA = {2,4,8,10};
        int[] arrB = {1,4,9};

        for (int x : arrA) a.enqueue(x);
        for (int x : arrB) b.enqueue(x);

        MyQueue<Integer> merged = merge(a, b);
        System.out.println(merged); // {1,2,4,4,8,9,10}
    }
}
