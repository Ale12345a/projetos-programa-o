public class User {
    private String name;
    private int steps;
    private int calories;

    public User(String name, int steps, int calories) {
        this.name = name;
        this.steps = steps;
        this.calories = calories;
    }

    public String getName() { return name; }
    public int getSteps() { return steps; }
    public int getCalories() { return calories; }
    public double getDistance() { return steps * 0.0008; } // aprox. 0.8 m por passo

    public void addSteps(int s) { steps += s; }
    public void addCalories(int c) { calories += c; }
}