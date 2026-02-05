public class Product {

    private final int id;
    private final String name;
    private int quantity;
    private final int minQuantity;

    public Product(int id, String name, int quantity, int minQuantity) {
        if (quantity < 0 || minQuantity < 0) {
            throw new IllegalArgumentException("Quantidades não podem ser negativas");
        }
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void increase(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }
        quantity += amount;
    }

    public void decrease(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }
        if (quantity < amount) {
            throw new IllegalStateException("Stock insuficiente");
        }
        quantity -= amount;
    }
}
