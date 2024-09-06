package Main;
import java.util.Scanner;

import BankingServices.BankingServiceImpl;
import Entity.Account;
import Entity.Customer;
import Entity.Transaction;

import java.time.LocalDateTime;

public class BankingSystemApp {
    private static final BankingServiceImpl bankingService = new BankingServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Banking System");
            System.out.println("1. Add Customers");
            System.out.println("2. Add Accounts");
            System.out.println("3. Add Transaction");
            System.out.println("4. Find Customer by Id");
            System.out.println("5. List all Accounts of specific Customer");
            System.out.println("6. List all transactions of specific Account");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    addAccount(scanner);
                    break;
                case 3:
                    addTransaction(scanner);
                    break;
                case 4:
                    findCustomerById(scanner);
                    break;
                case 5:
                    listAccountsByCustomerId(scanner);
                    break;
                case 6:
                    listTransactionsByAccountId(scanner);
                    break;
                case 7:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }

    private static void addCustomer(Scanner scanner) {
        System.out.println("Enter Customer Details");
        System.out.print("Customer Id: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Contact No.: ");
        String contact = scanner.nextLine();

        Customer customer = new Customer(customerId, name, address, contact);
        bankingService.addCustomer(customer);
        System.out.println("Customer added successfully.");
    }

    private static void addAccount(Scanner scanner) {
        System.out.println("Enter Account Details");
        System.out.print("Account Id: ");
        int accountId = scanner.nextInt();
        System.out.print("Customer Id: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Account Type (Saving/Current): ");
        String type = scanner.nextLine();
        System.out.print("Balance: ");
        double balance = scanner.nextDouble();

        Account account = new Account(accountId, customerId, type, balance);
        bankingService.addAccount(account);
        System.out.println("Account added successfully.");
    }

    private static void addTransaction(Scanner scanner) {
        System.out.println("Enter Transaction Details");
        System.out.print("Account Id: ");
        int accountId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Type (Deposit/Withdrawal): ");
        String type = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        LocalDateTime timestamp = LocalDateTime.now(); 

        int transactionId = bankingService.getAllTransactions().size() + 1; 
        Transaction transaction = new Transaction(transactionId, accountId, type, amount, timestamp);
        bankingService.addTransaction(transaction);
        System.out.println("Transaction added successfully.");
    }

    private static void findCustomerById(Scanner scanner) {
        System.out.print("Enter Customer Id: ");
        int customerId = scanner.nextInt();
        Customer customer = bankingService.findCustomerById(customerId);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void listAccountsByCustomerId(Scanner scanner) {
        System.out.print("Enter Customer Id: ");
        int customerId = scanner.nextInt();
        System.out.println("Accounts for Customer ID " + customerId + ":");
        for (Account account : bankingService.getAccountsByCustomerId(customerId)) {
            System.out.println(account);
        }
    }

    private static void listTransactionsByAccountId(Scanner scanner) {
        System.out.print("Enter Account Id: ");
        int accountId = scanner.nextInt();
        System.out.println("Transactions for Account ID " + accountId + ":");
        for (Transaction transaction : bankingService.getTransactionsByAccountId(accountId)) {
            System.out.println(transaction);
        }
    }
}