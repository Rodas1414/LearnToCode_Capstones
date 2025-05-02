package bonus;

public class Transaction {
    private String date;
    private String time;  // This is where 'time' should be declared
    private String description;
    private String vendor;
    private double amount;
    private String type; // "payment" or "deposit"

    // Constructor
    public Transaction(String date, String time, String description, String vendor, double amount, String type) {
        this.date = date;
        this.time = time;  // Ensure this is correctly assigned
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.type = type;
    }

    // Getter methods
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;  // This should return 'time'
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    // Method to return the transaction as a CSV formatted string
    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "|" + type;
    }

    // Optional: Method to display the transaction (can be used in the Ledger or Reports)
    @Override
    public String toString() {
        return "Date: " + date + ", Time: " + time + ", Description: " + description +
                ", Vendor: " + vendor + ", Amount: " + amount + ", Type: " + type;
    }
}
