import java.util.*;
import java.io.*;

public class Statistics {

    public static void showUserStatsWithGraph(List<User> users) {
        System.out.println("=== USER STATS ===");
        for (User user : users) {
            System.out.printf("%-10s | Steps: %-5d | ", user.getName(), user.getSteps());
            drawBar(user.getSteps(), 10000); // Meta 10.000 passos
            System.out.println();
        }
        System.out.println();
    }

    private static void drawBar(int value, int max) {
        int totalBlocks = 20;
        int filled = Math.min((int)((double)value / max * totalBlocks), totalBlocks);
        for (int i = 0; i < filled; i++) System.out.print("█");
        for (int i = filled; i < totalBlocks; i++) System.out.print("░");
    }

    public static void showDayStats(Map<String, int[]> dayStats) {
        System.out.println("=== DAY STATS ===");
        for (String day : dayStats.keySet()) {
            int[] stats = dayStats.get(day);
            System.out.println("Day: " + day + " | Steps: " + stats[0] + " | Calories: " + stats[1]);
        }
        System.out.println();
    }
}