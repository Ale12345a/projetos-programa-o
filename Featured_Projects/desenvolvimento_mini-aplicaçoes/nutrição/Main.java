import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int dailyCalorieGoal = 2000; // máximo recomendado
        User user = new User("Alex", dailyCalorieGoal);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            List<String> lines = Files.readAllLines(Paths.get("logs.txt"));
            for (String line : lines) {
                String[] parts = line.split("\\|");
                LocalDateTime timestamp = LocalDateTime.parse(parts[0].trim(), dtf);
                String mealName = parts[1].trim();
                int calories = Integer.parseInt(parts[2].trim());
                int protein = Integer.parseInt(parts[3].trim());
                int carbs = Integer.parseInt(parts[4].trim());
                int fat = Integer.parseInt(parts[5].trim());

                user.addMeal(new Meal(mealName, calories, protein, carbs, fat, timestamp));
            }
        } catch (Exception e) {
            System.out.println("Error reading logs: " + e.getMessage());
        }

        Statistics.showAllMeals(user);
        Statistics.showDailyStats(user);
        System.out.println(" Nutrition analysis complete!");
    }
}