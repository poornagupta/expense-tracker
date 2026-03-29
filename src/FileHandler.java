import java.io.*;
import java.util.*;

public class FileHandler {

    private static final String FILE_PATH = "data/expenses.txt";

    public static List<Expense> loadExpenses() {
        List<Expense> list = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Expense.fromFileString(line));
            }
        } catch (Exception e) {
            System.out.println("Error loading data");
        }

        return list;
    }

    public static void saveExpenses(List<Expense> expenses) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Expense e : expenses) {
                bw.write(e.toFileString());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving data");
        }
    }

    // CSV EXPORT
    public static void exportCSV(List<Expense> expenses) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/report.csv"))) {

            bw.write("Title,Amount,Category,Date\n");

            for (Expense e : expenses) {
                bw.write(e.getTitle() + "," + e.getAmount() + "," +
                         e.getCategory() + "," + e.getDate() + "\n");
            }

            System.out.println("CSV Exported!");

        } catch (Exception e) {
            System.out.println("Error exporting CSV");
        }
    }

    // MONTHLY REPORT CSV
    public static void exportMonthly(List<Expense> expenses) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/monthly.csv"))) {

            Map<String, Double> map = new TreeMap<>();

            for (Expense e : expenses) {
                String key = e.getDate().getYear() + "-" + e.getDate().getMonthValue();
                map.put(key, map.getOrDefault(key, 0.0) + e.getAmount());
            }

            bw.write("Month,Total\n");

            for (String m : map.keySet()) {
                bw.write(m + "," + map.get(m) + "\n");
            }

            System.out.println("Monthly Report Exported!");

        } catch (Exception e) {
            System.out.println("Error exporting monthly report");
        }
    }
}