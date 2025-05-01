<h1 align="center" style="color:#ff69b4;">💸 Accounting Ledger App</h1>

<p align="center">
  <b>by Rodas Gebreyohannes</b> <br>
  Java CLI application to manage your money with ✨style✨ and 💼 precision.
</p>

---

## 🌈 About the Project

> 🎯 Track your money like a boss.

This is a **Java Command-Line Interface (CLI)** application designed to:
- 💰 Add deposits
- 💸 Record payments
- 🧾 View a ledger
- 📊 Generate financial reports (like month-to-date, vendor-specific, etc.)

Everything is stored in a `.csv` file — no databases needed! 🗃️

---

## 🛠 Tech Stack

| Tool         | Purpose                  |
|--------------|--------------------------|
| ☕ Java       | Core programming language |
| 🧠 IntelliJ   | Code editor / IDE         |
| 🗂 CSV files  | Data storage              |
| 🧼 Terminal   | User interface            |

---

## ✨ Features

- 🔐 Securely stores all transactions
- 🔎 Filters by:
    - ✅ All Transactions
    - 💵 Deposits
    - 🧾 Payments
- 📅 Reports by:
    - 🕒 Date (month-to-date, previous month)
    - 🛍️ Vendor
- 🎯 Search functionality
- 📜 Sorts transactions by most recent

---

## 🧠 Code Highlight

Here’s how I filter and display transactions beautifully:

```java
private static void displayLedger(String filter) {
    List<Transaction> filtered = new ArrayList<>();
    for (Transaction t : transactions) {
        if (filter.equals("all") || 
           (filter.equals("deposit") && t.getType().equals("deposit")) ||
           (filter.equals("payment") && t.getType().equals("payment"))) {
            filtered.add(t);
        }
    }

    Collections.sort(filtered, Comparator.comparing(Transaction::getDate).reversed());
    filtered.forEach(System.out::println);
}
