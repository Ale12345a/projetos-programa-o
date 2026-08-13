import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        List<Integer> stepsList = new ArrayList<>();
        List<Integer> caloriesList = new ArrayList<>();

        FitnessTracker.loadLogs("logs.txt", users, dates, stepsList, caloriesList);

        Map<String, User> activeUsers = new LinkedHashMap<>();
        for (User u : users) {
            if (!activeUsers.containsKey(u.getName())) {
                activeUsers.put(u.getName(), new User(u.getName(), 0, 0));
            }
        }

        System.out.println("=== SMARTWATCH FITNESS MONITOR ===\n");

        for (int i = 0; i < users.size(); i++) {
            User logUser = users.get(i);
            String day = dates.get(i);
            int steps = stepsList.get(i);
            int calories = caloriesList.get(i);

            User currentUser = activeUsers.get(logUser.getName());
            currentUser.addSteps(steps);
            currentUser.addCalories(calories);

            // Limpar terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Mostrar stats
            Statistics.showUserStatsWithGraph(new ArrayList<>(activeUsers.values()));

            // Alertas
            for (User u : activeUsers.values()) {
                if (u.getSteps() >= 10000) {
                    System.out.println("⚠️ ALERT: " + u.getName() + " atingiu 10.000 passos!");
                }
            }

            // Stats diários
            Map<String, int[]> dayStats = DayStats.calculateDayStats(
                    new ArrayList<>(activeUsers.values()), 
                    dates.subList(0, i+1), 
                    stepsList.subList(0, i+1), 
                    caloriesList.subList(0, i+1)
            );
            Statistics.showDayStats(dayStats);

            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }

        System.out.println("✅ Monitorização completa!");
    }
}