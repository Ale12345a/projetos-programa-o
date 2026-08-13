public class Main {

    public static void main(String[] args) {

        TaskManager manager = new TaskManager();

        manager.addTask("Implement login", Priority.HIGH);
        manager.addTask("Write documentation", Priority.MEDIUM);
        manager.addTask("Fix bugs", Priority.HIGH);

        manager.updateStatus(1, Status.IN_PROGRESS);
        manager.updateStatus(3, Status.DONE);

        System.out.println("=== DONE TASKS ===");
        manager.getTasksByStatus(Status.DONE)
                .forEach(System.out::println);

        System.out.println("\n=== ALL TASKS ===");
        manager.printAllTasks();
    }
}
