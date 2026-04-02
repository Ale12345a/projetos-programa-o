import java.io.*;
import java.util.*;

public class LogService {

    public static List<LogEntry> readLogs(String file) {
        List<LogEntry> logs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");

                String timestamp = parts[0] + " " + parts[1];
                String action = parts[2];
                String user = parts[3];
                String ip = parts[4];
                String status = parts[5];

                logs.add(new LogEntry(timestamp, action, user, ip, status));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return logs;
    }

    // 🔥 Detectar ataques (muitos FAILED)
    public static void detectBruteForce(List<LogEntry> logs) {
        Map<String, Integer> failedAttempts = new HashMap<>();

        for (LogEntry log : logs) {
            if (log.status.equals("FAILED")) {
                failedAttempts.put(log.ip,
                        failedAttempts.getOrDefault(log.ip, 0) + 1);
            }
        }

        System.out.println("\n=== POSSIBLE ATTACKS ===");

        for (String ip : failedAttempts.keySet()) {
            if (failedAttempts.get(ip) >= 3) {
                System.out.println("⚠️ Suspicious IP: " + ip +
                        " (" + failedAttempts.get(ip) + " failed attempts)");
            }
        }
    }

    // 📊 Estatísticas por utilizador
    public static void userStats(List<LogEntry> logs) {
        Map<String, Integer> userCount = new HashMap<>();

        for (LogEntry log : logs) {
            userCount.put(log.user,
                    userCount.getOrDefault(log.user, 0) + 1);
        }

        System.out.println("\n=== USER ACTIVITY ===");

        for (String user : userCount.keySet()) {
            System.out.println(user + ": " + userCount.get(user) + " actions");
        }
    }
}