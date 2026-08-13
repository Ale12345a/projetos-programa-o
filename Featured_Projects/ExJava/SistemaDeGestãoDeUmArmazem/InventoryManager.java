import java.util.*;
import java.util.stream.Collectors;

public class InventoryManager {

    private final Map<Integer, Product> products = new HashMap<>();
    private final List<StockMovement> movements = new ArrayList<>();
    private int nextId = 1;

    public void addProduct(String name, int initialQuantity, int minQuantity) {
        Product product = new Product(nextId++, name, initialQuantity, minQuantity);
        products.put(product.getId(), product);
        movements.add(new StockMovement(product, initialQuantity));
    }

    public void increaseStock(int productId, int amount) {
        Product product = getProduct(productId);
        product.increase(amount);
        movements.add(new StockMovement(product, amount));
    }

    public void decreaseStock(int productId, int amount) {
        Product product = getProduct(productId);

        if (product.getQuantity() < amount) {
            throw new InsufficientStockException("Stock insuficiente para o produto " + product.getName());
        }

        product.decrease(amount);
        movements.add(new StockMovement(product, -amount));
    }

    public StockStatus getStockStatus(int productId) {
        Product product = getProduct(productId);

        if (product.getQuantity() == 0) {
            return StockStatus.OUT_OF_STOCK;
        }
        if (product.getQuantity() <= product.getMinQuantity()) {
            return StockStatus.LOW;
        }
        return StockStatus.OK;
    }

    public List<Product> listLowStockProducts() {
        return products.values().stream()
                .filter(p -> p.getQuantity() <= p.getMinQuantity())
                .collect(Collectors.toList());
    }

    private Product getProduct(int productId) {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("Produto não encontrado: " + productId);
        }
        return product;
    }
}