package AccountingLedger;

import java.io.*;
import java.util.*;

public class CSVUtility {

    // Method to read transactions from a CSV file
    public static List<Transaction> readTransactions(String filename) {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");  // Split by '|' as per your data format
                if (fields.length == 6) {
                    String date = fields[0];
                    String time = fields[1];
                    String description = fields[2];
                    String vendor = fields[3];
                    String type = fields[4];  // "P" or "D"
                    double amount = Double.parseDouble(fields[5]);

                    // If payment, make amount negative
                    if (type.equalsIgnoreCase("P")) {
                        amount = -Math.abs(amount);
                    }

                    Transaction transaction = new Transaction(date, time, description, vendor, amount, type);
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
        }

        return transactions;
    }

    // Method to write transactions to a CSV file
    public static void writeTransactions(String filename, List<Transaction> transactions) {
        try {
            File file = new File(filename);
            file.getParentFile().mkdirs();  // Create directories if they don’t exist

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (Transaction transaction : transactions) {
                    bw.write(transaction.toCSV());  // Write transaction as CSV
                    bw.newLine();
                }
            }

            System.out.println("Transactions successfully written to: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
