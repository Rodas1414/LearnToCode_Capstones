package bonus;

import java.io.*;
import java.util.*;

public class CSVUtility {

    public static List<Transaction> readTransactions(String filename) {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 1;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (fields.length != 6) {
                    System.err.println("Skipping invalid format at line " + lineNumber + ": " + line);
                    lineNumber++;
                    continue;
                }

                try {
                    String date = fields[0];
                    String time = fields[1];
                    String description = fields[2];
                    String vendor = fields[3];
                    double amount = Double.parseDouble(fields[4]);
                    String type = fields[5];

                    Transaction transaction = new Transaction(date, time, description, vendor, amount, type);
                    transactions.add(transaction);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid amount at line " + lineNumber + ": " + fields[4]);
                }

                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
        }

        return transactions;
    }
}