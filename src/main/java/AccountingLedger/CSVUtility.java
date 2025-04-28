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
                String[] fields = line.split("\\|");
                if (fields.length == 6) {
                    String date = fields[0];
                    String time = fields[1];
                    String description = fields[2];
                    String vendor = fields[3];
                    double amount = Double.parseDouble(fields[4]);
                    String type = fields[5];

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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Transaction transaction : transactions) {
                bw.write(transaction.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}

