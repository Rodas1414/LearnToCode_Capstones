package AccountingLedger;

import java.util.*;

public class FinancialTrackerApp {
    private static final String FILENAME = "AccountingLedger/transactions.csv";
    private static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        transactions = CSVUtility.readTransactions(FILENAME);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHome Screen:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit(scanner);
                    break;
                case "P":
                    makePayment(scanner);
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

    // Add a Deposit
    private static void addDeposit(Scanner scanner) {
        System.out.println("Enter deposit details:");

        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Time (HH:mm:ss): ");
        String time = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction deposit = new Transaction(date, time, description, vendor, amount, "deposit");
        transactions.add(deposit);
        CSVUtility.writeTransactions(FILENAME, transactions);

        System.out.println("Deposit added successfully.");
    }

    // Make a Payment (Debit)
    private static void makePayment(Scanner scanner) {
        System.out.println("Enter payment details:");

        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Time (HH:mm:ss): ");
        String time = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // Negative amount for payments
        Transaction payment = new Transaction(date, time, description, vendor, -amount, "payment");
        transactions.add(payment);
        CSVUtility.writeTransactions(FILENAME, transactions);

        System.out.println("Payment added successfully.");
    }

    // Ledger Screen
    private static void ledgerScreen(Scanner scanner) {
        while (true) {
            System.out.println("\nLedger Screen:");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            String choice = scanner.nextLine().toUpperCase();

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
                    return; // Go back to Home Screen
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Display Ledger entries
    private static void displayLedger(String filter) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (filter.equals("all") ||
                    (filter.equals("deposit") && transaction.getType().equals("deposit")) ||
                    (filter.equals("payment") && transaction.getType().equals("payment"))) {
                filteredTransactions.add(transaction);
            }
        }
        Collections.sort(filteredTransactions, Comparator.comparing(Transaction::getDate).reversed());

        for (Transaction transaction : filteredTransactions) {
            System.out.println(transaction);
        }
    }

    // Reports Screen
    private static void reportsScreen(Scanner scanner) {
        while (true) {
            System.out.println("\nReports Screen:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Month to Date report logic here
                    break;
                case "2":
                    // Previous Month report logic here
                    break;
                case "3":
                    // Year to Date report logic here
                    break;
                case "4":
                    // Previous Year report logic here
                    break;
                case "5":
                    searchByVendor(scanner);
                    break;
                case "0":
                    return; // Go back to the Ledger Screen
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Search transactions by vendor
    private static void searchByVendor(Scanner scanner) {
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();

        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transaction);
            }
        }
    }

    // Exit the application
    private static void exitApplication() {
        System.out.println("Exiting the application...");
    }
}