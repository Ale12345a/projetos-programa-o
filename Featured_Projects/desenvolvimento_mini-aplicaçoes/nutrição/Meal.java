import java.time.LocalDateTime;

public class Meal {
    private String name;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;
    private LocalDateTime timestamp;

    public Meal(String name, int calories, int protein, int carbs, int fat, LocalDateTime timestamp) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.timestamp = timestamp;
    }

    public String getName() { return name; }
    public int getCalories() { return calories; }
    public int getProtein() { return protein; }
    public int getCarbs() { return carbs; }
    public int getFat() { return fat; }
    public LocalDateTime getTimestamp() { return timestamp; }
}