public class Task {

    private final int id;
    private String title;
    private Priority priority;
    private Status status;

    public Task(int id, String title, Priority priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.status = Status.TODO;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}
