import java.util.ArrayList;
import java.util.List;

public class Order {

    private final int id;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.CREATED;
    private PaymentMethod paymentMethod;

    public Order(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void addItem(Product product, int quantity) {
        if (status == OrderStatus.SHIPPED || status == OrderStatus.CANCELLED) {
            throw new InvalidOrderException("Cannot modify a closed order");
        }
        items.add(new OrderItem(product, quantity));
    }

    public void pay(PaymentMethod method) {
        if (status != OrderStatus.CREATED) {
            throw new InvalidStateTransitionException("Order cannot be paid");
        }
        this.paymentMethod = method;
        this.status = OrderStatus.PAID;
    }

    public void ship() {
        if (status != OrderStatus.PAID) {
            throw new InvalidStateTransitionException("Order cannot be shipped");
        }
        this.status = OrderStatus.SHIPPED;
    }

    public double calculateTotal() {
        return items.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
    }
}
