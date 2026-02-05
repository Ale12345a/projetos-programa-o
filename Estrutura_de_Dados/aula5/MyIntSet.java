// Implementação da interface IntSet
public class MyIntSet implements IntSet {
    private boolean[] present; // marca se um número está no conjunto
    private int count;         // número de elementos no conjunto

    // Construtor padrão
    public MyIntSet() {
        present = new boolean[1001]; // valores de 1 até 1000
        count = 0;
    }

    @Override
    public boolean contains(int x) {
        if (x < 1 || x > 1000) return false;
        return present[x];
    }

    @Override
    public boolean add(int x) {
        if (x < 1 || x > 1000) return false;
        if (!present[x]) {
            present[x] = true;
            count++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(int x) {
        if (x < 1 || x > 1000) return false;
        if (present[x]) {
            present[x] = false;
            count--;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        for (int i = 1; i <= 1000; i++) {
            present[i] = false;
        }
        count = 0;
    }
}