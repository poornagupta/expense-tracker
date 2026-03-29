import java.time.LocalDate;

public class Expense {
    private String title;
    private double amount;
    private String category;
    private LocalDate date;

    public Expense(String title, double amount, String category, LocalDate date) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String getTitle() { return title; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public LocalDate getDate() { return date; }

    public String toFileString() {
        return title + "," + amount + "," + category + "," + date;
    }

    public static Expense fromFileString(String line) {
        String[] p = line.split(",");
        return new Expense(p[0], Double.parseDouble(p[1]), p[2], LocalDate.parse(p[3]));
    }
}