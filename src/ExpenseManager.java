import java.util.*;

public class ExpenseManager {

    private List<Expense> expenses;

    public ExpenseManager() {
        expenses = FileHandler.loadExpenses();
    }

    public void addExpense(String t, double a, String c) {
        expenses.add(new Expense(t, a, c, java.time.LocalDate.now()));
        FileHandler.saveExpenses(expenses);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void deleteExpense(int index) {
        if (index >= 1 && index <= expenses.size()) {
            expenses.remove(index - 1);
            FileHandler.saveExpenses(expenses);
        }
    }

    public double getTotal() {
        double total = 0;
        for (Expense e : expenses) total += e.getAmount();
        return total;
    }

    public Map<String, Double> categoryReport() {
        Map<String, Double> map = new HashMap<>();
        for (Expense e : expenses) {
            map.put(e.getCategory(),
                map.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }
        return map;
    }

    public Map<String, Double> monthlyReport() {
        Map<String, Double> map = new TreeMap<>();
        for (Expense e : expenses) {
            String key = e.getDate().getYear() + "-" + e.getDate().getMonthValue();
            map.put(key, map.getOrDefault(key, 0.0) + e.getAmount());
        }
        return map;
    }

    public void exportCSV() { FileHandler.exportCSV(expenses); }
    public void exportMonthly() { FileHandler.exportMonthly(expenses); }
}