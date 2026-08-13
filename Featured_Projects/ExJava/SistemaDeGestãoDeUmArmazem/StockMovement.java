import java.time.LocalDateTime;

public class StockMovement {

    private final LocalDateTime date;
    private final Product product;
    private final int change;

    public StockMovement(Product product, int change) {
        this.date = LocalDateTime.now();
        this.product = product;
        this.change = change;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Product getProduct() {
        return product;
    }

    public int getChange() {
        return change;
    }
}
