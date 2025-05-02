package AccountingLedger;

import java.util.*;
import static AccountingLedger.CSVUtility.readTransactions;

public class FinancialTrackerApp {
    private static final String FILENAME = "src/transactions.csv";
    private static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        transactions = readTransactions(FILENAME);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHome Screen:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addTransaction(scanner, "deposit");
                    break;
                case "P":
                    addTransaction(scanner, "payment");
                    break;
                case "L":
                    ledgerScreen(scanner);
                    break;
                case "X":
                    exitApplication();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTransaction(Scanner scanner, String type) {
        System.out.println("Enter " + type + " details:");

        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Time (HH:mm:ss): ");
        String time = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        double amount = 0.0;
        try {
            System.out.print("Amount: ");
            amount = Double.parseDouble(scanner.nextLine());
            if (type.equals("payment")) {
                amount = -amount; // Negative for payments
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Transaction cancelled.");
            return;
        }

        Transaction transaction = new Transaction(date, time, description, vendor, amount, type);
        transactions.add(transaction);
        CSVUtility.writeTransactions(FILENAME, transactions);

        System.out.println(type.substring(0, 1).toUpperCase() + type.substring(1) + " added successfully.");
    }

    private static void ledgerScreen(Scanner scanner) {
        while (true) {
            System.out.println("\nLedger Screen:");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    displayLedger("all");
                    break;
                case "D":
                    displayLedger("deposit");
                    break;
                case "P":
                    displayLedger("payment");
                    break;
                case "R":
                    reportsScreen(scanner);
                    break;
                case "H":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayLedger(String filter) {
        List<Transaction> filtered = new ArrayList<>();
        for (Transaction t : transactions) {
            if (filter.equals("all") || t.getType().equals(filter)) {
                filtered.add(t);
            }
        }
        filtered.sort(Comparator.comparing(Transaction::getDate).reversed());

        for (Transaction t : filtered) {
            System.out.println(t);
        }
    }

    private static void reportsScreen(Scanner scanner) {
        while (true) {
            System.out.println("\nReports Screen:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Add Month-to-Date logic
                    break;
                case "2":
                    // Add Previous Month logic
                    break;
                case "3":
                    // Add Year-to-Date logic
                    break;
                case "4":
                    // Add Previous Year logic
                    break;
                case "5":
                    searchByVendor(scanner);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void searchByVendor(Scanner scanner) {
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();

        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(t);
            }
        }
    }

    private static void exitApplication() {
        System.out.println("Exiting the application...");
    }
}