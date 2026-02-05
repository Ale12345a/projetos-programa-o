import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private final List<Order> orders = new ArrayList<>();
    private int nextOrderId = 1;

    public Order createOrder() {
        Order order = new Order(nextOrderId++);
        orders.add(order);
        return order;
    }

    private Order findOrder(int orderId) {
        return orders.stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .orElseThrow(() -> new InvalidOrderException("Order not found"));
    }

    public void addProductToOrder(int orderId, Product product, int quantity) {
        findOrder(orderId).addItem(product, quantity);
    }

    public void payOrder(int orderId, PaymentMethod method) {
        findOrder(orderId).pay(method);
    }

    public void shipOrder(int orderId) {
        findOrder(orderId).ship();
    }

    public double calculateTotal(int orderId) {
        return findOrder(orderId).calculateTotal();
    }
}
