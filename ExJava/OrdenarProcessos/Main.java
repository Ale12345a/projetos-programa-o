public class Main {

    public static void main(String[] args) {

        OrderManager manager = new OrderManager();

        Product laptop = new Product(1, "Laptop", 1200.0);
        Product mouse  = new Product(2, "Mouse", 25.0);

        Order order = manager.createOrder();

        manager.addProductToOrder(order.getId(), laptop, 1);
        manager.addProductToOrder(order.getId(), mouse, 2);

        System.out.println("Total: €" + manager.calculateTotal(order.getId()));

        manager.payOrder(order.getId(), PaymentMethod.MBWAY);
        manager.shipOrder(order.getId());

        // operação inválida
        try {
            manager.addProductToOrder(order.getId(), mouse, 1);
        } catch (InvalidOrderException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }
    }
}
    