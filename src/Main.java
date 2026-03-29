import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        while (true) {   // ✅ FIXED HERE

            System.out.println("\n===== Expense Tracker =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Category Report");
            System.out.println("5. Monthly Report");
            System.out.println("6. Total Expense");
            System.out.println("7. Export CSV");
            System.out.println("8. Export Monthly Report");
            System.out.println("9. Exit");
            System.out.print("Choose option: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Enter number.");
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter amount: ");
                    double amount;
                    try {
                        amount = Double.parseDouble(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid amount!");
                        break;
                    }

                    System.out.print("Enter category: ");
                    String category = sc.nextLine();

                    manager.addExpense(title, amount, category);
                    System.out.println("Expense added!");
                    break;

                case 2:
                    if (manager.getExpenses().isEmpty()) {
                        System.out.println("No expenses found.");
                        break;
                    }

                    int i = 1;
                    for (Expense e : manager.getExpenses()) {
                        System.out.println(i++ + ". " +
                                e.getTitle() + " | ₹" +
                                e.getAmount() + " | " +
                                e.getCategory() + " | " +
                                e.getDate());
                    }
                    break;

                case 3:
                    System.out.print("Enter index to delete: ");
                    try {
                        int index = Integer.parseInt(sc.nextLine());
                        manager.deleteExpense(index);
                        System.out.println("Deleted!");
                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                    }
                    break;

                case 4:
                    System.out.println("\nCategory Report:");
                    manager.categoryReport().forEach((k, v) ->
                            System.out.println(k + " : ₹" + v));
                    break;

                case 5:
                    System.out.println("\nMonthly Report:");
                    manager.monthlyReport().forEach((k, v) ->
                            System.out.println(k + " : ₹" + v));
                    break;

                case 6:
                    System.out.println("Total Expense: ₹" + manager.getTotal());
                    break;

                case 7:
                    manager.exportCSV();
                    break;

                case 8:
                    manager.exportMonthly();
                    break;

                case 9:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        } // ✅ loop ends here
    }
}