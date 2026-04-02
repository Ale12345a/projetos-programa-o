public class LogEntry {
    String timestamp;
    String action;
    String user;
    String ip;
    String status;

    public LogEntry(String timestamp, String action, String user, String ip, String status) {
        this.timestamp = timestamp;
        this.action = action;
        this.user = user;
        this.ip = ip;
        this.status = status;
    }
}