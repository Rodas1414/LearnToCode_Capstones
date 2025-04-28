package AccountingLedger;

public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;
    private String type;  // "deposit" or "payment"

    public Transaction(String date, String time, String description, String vendor, double amount, String type) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.type = type;
    }

    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getDescription() { return description; }
    public String getVendor() { return vendor; }
    public double getAmount() { return amount; }
    public String getType() { return type; }

    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "|" + type;
    }

    @Override
    public String toString() {
        return date + " " + time + " | " + description + " | " + vendor + " | " + amount + " | " + type;
    }
}

