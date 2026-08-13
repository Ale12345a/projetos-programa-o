import java.util.*;

public class DayStats {

    public static Map<String, int[]> calculateDayStats(List<User> users, List<String> dates, List<Integer> stepsList, List<Integer> caloriesList) {
        Map<String, int[]> dayStats = new LinkedHashMap<>();

        for (int i = 0; i < dates.size(); i++) {
            String day = dates.get(i).split(" ")[0];
            dayStats.putIfAbsent(day, new int[]{0,0});
            int[] stats = dayStats.get(day);
            stats[0] += stepsList.get(i);
            stats[1] += caloriesList.get(i);
        }

        return dayStats;
    }
}