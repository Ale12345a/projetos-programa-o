public class Main {

    public static void main(String[] args) {

        InventoryManager manager = new InventoryManager();

        manager.addProduct("Teclado", 10, 3);
        manager.addProduct("Rato", 5, 2);
        manager.addProduct("Monitor", 1, 2);

        manager.increaseStock(1, 5);
        manager.decreaseStock(2, 3);

        System.out.println("Estado do stock:");
        System.out.println("Teclado: " + manager.getStockStatus(1));
        System.out.println("Rato: " + manager.getStockStatus(2));
        System.out.println("Monitor: " + manager.getStockStatus(3));

        System.out.println("\nProdutos com stock baixo:");
        manager.listLowStockProducts()
                .forEach(p -> System.out.println(p.getName() + " (" + p.getQuantity() + ")"));
    }
}
