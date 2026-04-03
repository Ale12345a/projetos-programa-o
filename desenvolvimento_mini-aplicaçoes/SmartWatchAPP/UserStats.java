import java.util.HashMap;
import java.util.Map;

public class UserStats {
    private Map<String, Integer> stepsPerUser;
    private Map<String, Integer> caloriesPerUser;

    public UserStats() {
        stepsPerUser = new HashMap<>();
        caloriesPerUser = new HashMap<>();
    }

    public void addWorkout(String user, int steps, int calories) {
        stepsPerUser.put(user, stepsPerUser.getOrDefault(user, 0) + steps);
        caloriesPerUser.put(user, caloriesPerUser.getOrDefault(user, 0) + calories);
    }

    public void printStats() {
        System.out.println("=== USER STATS ===");
        for (String user : stepsPerUser.keySet()) {
            System.out.println("User: " + user + " | Steps: " + stepsPerUser.get(user) +
                    " | Calories: " + caloriesPerUser.get(user));
        }
        System.out.println();
    }
}