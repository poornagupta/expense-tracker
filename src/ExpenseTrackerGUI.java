import javax.swing.*;
import java.awt.*;

public class ExpenseTrackerGUI {

    public ExpenseTrackerGUI() {
        ExpenseManager manager = new ExpenseManager();

        JFrame f = new JFrame("Expense Tracker");
        f.setSize(500, 500);

        JPanel p = new JPanel(new GridLayout(9,1));
        JTextArea output = new JTextArea();

        JButton add = new JButton("Add");
        JButton view = new JButton("View");
        JButton del = new JButton("Delete");
        JButton total = new JButton("Total");
        JButton cat = new JButton("Category");
        JButton month = new JButton("Monthly");
        JButton csv = new JButton("Export CSV");
        JButton mCsv = new JButton("Export Monthly");

        add.addActionListener(e -> {
            String t = JOptionPane.showInputDialog("Title");
            double a = Double.parseDouble(JOptionPane.showInputDialog("Amount"));
            String c = JOptionPane.showInputDialog("Category");
            manager.addExpense(t,a,c);
        });

        view.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            int i=1;
            for(Expense ex: manager.getExpenses()){
                sb.append(i++ + ". " + ex.getTitle()+" ₹"+ex.getAmount()+" "+ex.getCategory()+"\n");
            }
            output.setText(sb.toString());
        });

        del.addActionListener(e -> {
            int i = Integer.parseInt(JOptionPane.showInputDialog("Index"));
            manager.deleteExpense(i);
        });

        total.addActionListener(e -> output.setText("Total: ₹"+manager.getTotal()));
        cat.addActionListener(e -> output.setText(manager.categoryReport().toString()));
        month.addActionListener(e -> output.setText(manager.monthlyReport().toString()));

        csv.addActionListener(e -> manager.exportCSV());
        mCsv.addActionListener(e -> manager.exportMonthly());

        p.add(add);p.add(view);p.add(del);p.add(total);
        p.add(cat);p.add(month);p.add(csv);p.add(mCsv);

        f.add(p, BorderLayout.NORTH);
        f.add(new JScrollPane(output), BorderLayout.CENTER);

        f.setVisible(true);
    }

    public static void main(String[] args) {
        new ExpenseTrackerGUI();
    }
}