import java.util.*;
import java.io.*;

public class FitnessTracker {

    public static void loadLogs(String filename, List<User> users, List<String> dates, List<Integer> stepsList, List<Integer> caloriesList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String date = parts[0] + " " + parts[1];
                String name = parts[2];
                int steps = Integer.parseInt(parts[3]);
                int calories = Integer.parseInt(parts[4]);

                users.add(new User(name, steps, calories));
                dates.add(date);
                stepsList.add(steps);
                caloriesList.add(calories);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler logs: " + e.getMessage());
        }
    }
}