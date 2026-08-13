public class Product {

    private final int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " (€" + price + ")";
    }
}
