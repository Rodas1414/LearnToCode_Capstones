# 💰 **Financial Tracker Application** 💸

Welcome to the **Financial Tracker Application**! 🎉 This app helps you easily manage and track your personal finances, offering the ability to add deposits, make payments, and generate financial reports. 💵

## 📸 **Screenshots**

### 🏠 **Home Screen**
The **Home Screen** is where you can choose your next action: adding a deposit, making a payment, viewing the ledger, or exiting the application.

![Home Screen](src/Image/home.png)

### 📊 **Report Screen**
The **Report Screen** lets you generate various financial reports based on your transactions.

![Report Screen](src/Image/report.png)

### 📑 **Ledger Screen**
The **Ledger Screen** displays your entire transaction history in an easy-to-read format.

![Ledger Screen](src/Image/ledger.png)

## 🖥️ **Features**

- 💸 **Add Deposit**: Allows you to record income or any incoming funds.
- 💳 **Make Payment (Debit)**: Record any outgoing transactions such as bills, purchases, or payments.
- 🧾 **Ledger**: View a full list of all your transactions with detailed information.
- 📈 **Reports**: Generate financial reports to analyze your spending or income over specific periods.

## 🔍 **Code Structure**

The **Financial Tracker Application** is built using Java and follows a simple object-oriented design. Below is an overview of the classes and their responsibilities:

### 1. **`FinancialTrackerApp`** (Main Application)

The entry point to the application. It runs the main program loop, offering a simple text-based interface for the user to interact with.

- **Features**:
   - Display home screen options.
   - Allow user to add deposits, make payments, view the ledger, or exit.
   - Interact with the `CSVUtility` class to read and write transaction data.

### 2. **`Transaction`** (Represents Financial Transactions)

Represents an individual financial transaction (either a deposit or payment). This class holds the transaction’s attributes such as:

- Date
- Time
- Description
- Vendor
- Amount 💵
- Type (deposit or payment)

This class also has methods to format the transaction details into a CSV-friendly format.

### 3. **`CSVUtility`** (Handles File I/O)

This utility class provides methods for reading and writing transaction data from/to a CSV file.

- **`readTransactions`**: Reads the transaction records from a specified CSV file and loads them into a list of `Transaction` objects.
- **`writeTransactions`**: Writes the updated list of `Transaction` objects back into the CSV file, ensuring the records are saved.

### 4. **`Reports`** (Generates Reports)

Responsible for creating financial reports such as:

- **Month-to-date** (MTD) 📅
- **Previous month** 📅
- **Year-to-date** (YTD) 📅
- **Previous year** 📅
- **Search by vendor** 🏢

This module allows users to generate reports based on transaction data, helping them analyze their finances over specific periods.

## 🔧 **Technologies Used**

- **Java**: The programming language used to build this application.
- **CSV Files**: Used for storing transaction data.
- **Scanner & BufferedReader**: For reading user input and reading/writing to CSV files.
- **Simple CLI Interface**: The application runs in the console for a simple, no-frills user experience.

## 📁 Project File Structure

- **src**
   - **main**
      - **java**
         - **AccountingLedger**
            - `FinancialTrackerApp.java` – Main application logic
            - `Transaction.java` – Represents a financial transaction
            - `CSVUtility.java` – Handles reading/writing transactions to CSV
   - **Image**
      - `home.png` – 🏠 Home screen UI
      - `ledger.png` – 📑 Ledger screen UI
      - `report.png` – 📊 Report screen UI

- `transactions.csv` – 💾 Stores all transaction records
- `README.md` – 📚 Project documentation


## 💡 **How to Use**

1. **Run the Application**:
   - Download or clone the project from GitHub.
   - Compile the `FinancialTrackerApp.java` file using your Java IDE or command line.
   - Run the application, and you'll be presented with a simple CLI interface.

2. **Add a Deposit**: 💸
   - Enter details such as the date, description, vendor, and amount.

3. **Make a Payment (Debit)**: 💳
   - Similar to adding a deposit, but the amount will be recorded as a negative value.

4. **View the Ledger**: 📑
   - See all your transactions listed with the option to filter by type (deposit or payment).

5. **Generate Reports**: 📊
   - Generate reports for a specific period (month, year, etc.) or search by vendor.




**Made with ❤️ by [Rodas](https://github.com/Rodas1414)**


