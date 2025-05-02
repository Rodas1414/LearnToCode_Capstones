package bonus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

public class LedgerAppGUI extends JFrame {
    private final String FILE_NAME = "transactions.csv";
    private JTable table;
    private DefaultTableModel tableModel;

    public LedgerAppGUI() {
        setTitle("Accounting Ledger Application");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Table Model
        tableModel = new DefaultTableModel(new String[]{"Date", "Time", "Description", "Vendor", "Amount", "Type"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton loadBtn = new JButton("Load");
        JButton saveBtn = new JButton("Save");
        JButton addBtn = new JButton("Add Transaction");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(loadBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(exitBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Actions
        loadBtn.addActionListener(e -> loadData());
        saveBtn.addActionListener(e -> saveData());
        addBtn.addActionListener(e -> addTransactionDialog());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Transaction> transactions = CSVUtility.readTransactions(FILE_NAME);
        for (Transaction t : transactions) {
            tableModel.addRow(new Object[]{
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(),
                    t.getAmount(), t.getType()
            });
        }
        JOptionPane.showMessageDialog(this, "Loaded " + transactions.size() + " transactions.");
    }

    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String line = String.join("|",
                        tableModel.getValueAt(i, 0).toString(),
                        tableModel.getValueAt(i, 1).toString(),
                        tableModel.getValueAt(i, 2).toString(),
                        tableModel.getValueAt(i, 3).toString(),
                        tableModel.getValueAt(i, 4).toString(),
                        tableModel.getValueAt(i, 5).toString()
                );
                writer.write(line);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(this, "Saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file.");
        }
    }

    private void addTransactionDialog() {
        JTextField dateField = new JTextField();
        JTextField timeField = new JTextField();
        JTextField descField = new JTextField();
        JTextField vendorField = new JTextField();
        JTextField amountField = new JTextField();
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"payment", "deposit"});

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        panel.add(dateField);
        panel.add(new JLabel("Time (HH:mm:ss):"));
        panel.add(timeField);
        panel.add(new JLabel("Description:"));
        panel.add(descField);
        panel.add(new JLabel("Vendor:"));
        panel.add(vendorField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("Type:"));
        panel.add(typeBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Transaction",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String type = typeBox.getSelectedItem().toString();

                tableModel.addRow(new Object[]{
                        dateField.getText(),
                        timeField.getText(),
                        descField.getText(),
                        vendorField.getText(),
                        amount,
                        type
                });
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount format.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LedgerAppGUI().setVisible(true);
        });
    }
}
