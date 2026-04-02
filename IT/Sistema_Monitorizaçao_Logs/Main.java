import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<LogEntry> logs = LogService.readLogs("logs.txt");

        System.out.println("Logs loaded: " + logs.size());

        LogService.userStats(logs);
        LogService.detectBruteForce(logs);
    }
}