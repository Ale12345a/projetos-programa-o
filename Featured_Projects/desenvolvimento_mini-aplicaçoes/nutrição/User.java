import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Meal> meals;
    private int dailyCalorieGoal; // novo

    public User(String name, int dailyCalorieGoal) {
        this.name = name;
        this.meals = new ArrayList<>();
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    public String getName() { return name; }
    public List<Meal> getMeals() { return meals; }
    public int getDailyCalorieGoal() { return dailyCalorieGoal; }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }
}