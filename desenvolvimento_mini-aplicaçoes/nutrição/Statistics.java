import java.time.format.DateTimeFormatter;
import java.util.*;

public class Statistics {

    public static void showAllMeals(User user) {
        System.out.println("=== Meals for " + user.getName() + " ===");
        for (Meal meal : user.getMeals()) {
            System.out.printf("%s | %s | Calories: %d | Protein: %d | Carbs: %d | Fat: %d\n",
                    meal.getTimestamp().toLocalDate(),
                    meal.getName(),
                    meal.getCalories(),
                    meal.getProtein(),
                    meal.getCarbs(),
                    meal.getFat());
        }
        System.out.println();
    }

    public static void showDailyStats(User user) {
        System.out.println("=== Daily Stats ===");
        Map<String, int[]> dailyTotals = new TreeMap<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Meal meal : user.getMeals()) {
            String day = meal.getTimestamp().format(df);
            dailyTotals.putIfAbsent(day, new int[4]); // calories, protein, carbs, fat
            int[] totals = dailyTotals.get(day);
            totals[0] += meal.getCalories();
            totals[1] += meal.getProtein();
            totals[2] += meal.getCarbs();
            totals[3] += meal.getFat();
        }

        for (String day : dailyTotals.keySet()) {
            int[] totals = dailyTotals.get(day);
            String status;
            if (totals[0] <= user.getDailyCalorieGoal()) {
                status = "✅ Within goal";
            } else {
                status = "⚠️ Exceeded goal";
            }
            System.out.printf("Day: %s | Calories: %d | Protein: %d | Carbs: %d | Fat: %d | %s\n",
                    day, totals[0], totals[1], totals[2], totals[3], status);
        }
        System.out.println();
    }
}