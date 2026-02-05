import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {

    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    // ➕ Criar tarefa
    public void addTask(String title, Priority priority) {
        Task task = new Task(nextId++, title, priority);
        tasks.add(task);
    }

    // 🔍 Método auxiliar (privado)
    private Task findTaskById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    // 🔄 Atualizar estado
    public void updateStatus(int id, Status newStatus) {
        Task task = findTaskById(id);
        task.setStatus(newStatus);
    }

    // ❌ Remover tarefa
    public void removeTask(int id) {
        Task task = findTaskById(id);
        tasks.remove(task);
    }

    // 📂 Filtrar por estado
    public List<Task> getTasksByStatus(Status status) {
        return tasks.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

    // 📊 Imprimir todas (ordenadas por prioridade)
    public void printAllTasks() {
        tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority).reversed())
                .forEach(System.out::println);
    }
}
