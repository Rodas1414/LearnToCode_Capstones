package AccountingLedger;
import bonus.Transaction;

import java.io.*;
import java.util.*;

public class CSVUtility {

    public static List<Transaction> readTransactions(String filename) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 6) {
                    String date = data[0];
                    String time = data[1];
                    String description = data[2];
                    String vendor = data[3];
                    double amount = Double.parseDouble(data[4]);
                    String type = data[5];
                    transactions.add(new Transaction(date, time, description, vendor, amount, type));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public static void writeTransactions(String filename, List<Transaction> transactions) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Transaction transaction : transactions) {
                bw.write(String.format("%s|%s|%s|%s|%.2f|%s\n", transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(), transaction.getAmount(), transaction.getType()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
