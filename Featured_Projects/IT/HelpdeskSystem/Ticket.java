import java.time.LocalDate;

public class Ticket {
    int id;
    String title;
    String priority;
    String status;
    LocalDate createdAt;
    LocalDate resolvedAt;
    String department;

    public Ticket(int id, String title, String priority, String status,
                  LocalDate createdAt, LocalDate resolvedAt, String department) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.resolvedAt = resolvedAt;
        this.department = department;
    }
}