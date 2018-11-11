package org.pursuit;

import java.util.*;

public class Customer extends BankTeller{
    Scanner scanner = new Scanner(System.in);
    StringBuilder checkingAccount = new StringBuilder();
    double checkingBalance;
    StringBuilder savingsAccount = new StringBuilder();
    double savingsBalance;

    public Customer() {}

    public Customer(String name, double startChecking, double startSavings) {
        this.checkingAccount.append(name);
        this.checkingBalance = startChecking;
        this.savingsAccount.append(name);
        this.savingsBalance = startSavings;
    }

    protected void startCustomerApp() {
        System.out.println("\nWelcome to the Customer portal!");
        customerAppMenu();
    }

    protected void customerAppMenu() {
        int customerMenuChoice;
        do {
            System.out.println("\nWhat would you like to do? Please select one of the following options: ");
            System.out.println("1. Create a new checking account");
            System.out.println("2. Create a new savings account");
            System.out.println("3. View account balances");
            System.out.println("4. Make a deposit");
            System.out.println("5. Make a Withdrawal");
            System.out.println("6. Close an account");
            System.out.println("7. Exit app\n");
            customerMenuChoice = scanner.nextInt();
            switch (customerMenuChoice) {
                case 1:
                    createCheckingAccount();
                    break;
                case 2:
                    createSavingsAccount();
                    break;
                case 3:
                    getAccountBalances();
                    break;
                case 4:
                    deposit();
                    break;
                case 5:
                    withdrawal();
                    break;
                case 6:
                    closeAccount();
                    break;
            }
            if (customerMenuChoice == 7) {
                System.out.println("\nThank you for being a valued customer. Goodbye!");
                break;
            }
        } while (customerMenuChoice != 7);
    }

    protected void createCheckingAccount() {
        System.out.println("\nPlease enter the first and last name for the new checking account: ");
        scanner.nextLine();
        String customerName = scanner.nextLine();
        this.checkingAccount.append(customerName);
        System.out.println("\nPlease enter the starting balance amount in dollars and cents: ");
        this.checkingBalance = scanner.nextDouble();
        allCheckingAccountsMap.put(checkingAccount, checkingBalance); // adds new account bank's accounts list
        if (!allBankCustomers.contains(checkingAccount)) {
            allBankCustomers.add(checkingAccount); // adds new customers only to list of customers
        }
        System.out.println("\nChecking account successfully created for " + checkingAccount + " with a balance of " + checkingBalance);
        scanner.nextLine();
    }

    protected void createSavingsAccount() {
        System.out.println("\nPlease enter the first and last name for the new savings account: ");
        scanner.nextLine();
        String customerName = scanner.nextLine();
        this.savingsAccount.append(customerName);
        System.out.println("\nPlease enter the starting balance amount in dollars and cents: ");
        this.savingsBalance = scanner.nextDouble();
        allSavingsAccountsMap.put(savingsAccount, savingsBalance); // adds new account bank's accounts list
        if (!allBankCustomers.contains(savingsAccount)) {
            allBankCustomers.add(savingsAccount);
        }
        System.out.println("\nChecking account successfully created for " + savingsAccount + " with a balance of " + savingsBalance);
        scanner.nextLine();
    }

    protected void deposit() {
        System.out.println("\nPlease select the account to deposit to: ");
        System.out.println("\nEnter '1' for checking or '2' for savings");
        int depositToThisAccount = scanner.nextInt();
        System.out.println("\nPlease enter the amount to deposit in dollars and cents: ");
        double depositAmount = scanner.nextDouble();
        switch (depositToThisAccount) {
            case 1:
                checkingBalance += depositAmount;
                System.out.println("\nTransaction successful. Checking balance: " + checkingBalance);
                break;
            case 2:
                savingsBalance += depositAmount;
                System.out.println("\nTransaction successful. Savings balance: " + savingsBalance);
                break;
            default:
                System.out.println("\nTransaction cancelled.");
                break;
        }
    }

    protected void withdrawal() {
        System.out.println("\nPlease select the account to withdraw from: ");
        System.out.println("\nEnter '1' for checking or '2' for savings");
        int withdrawFromThisAccount = scanner.nextInt();
        System.out.println("\nPlease enter the amount to withdraw in dollars and cents: ");
        double withdrawalAmount = scanner.nextDouble();
        switch (withdrawFromThisAccount) {
            case 1:
                if (withdrawalAmount <= checkingBalance) {
                    checkingBalance -= withdrawalAmount;
                    System.out.println("\nTransaction successful. Available checking: " + checkingBalance);
                }
                break;
            case 2:
                if (withdrawalAmount <= savingsBalance) {
                    savingsBalance -= withdrawalAmount;
                    System.out.println("\nTransaction successful. Available savings: " + savingsBalance);
                }
                break;
            default:
                System.out.println("\nTransaction cancelled.");
                break;
        }
    }

    protected void closeAccount() {
        System.out.println("\nAre you sure you want to close an account? Y/n");
        scanner.nextLine();
        String closeConfirmation = scanner.nextLine();
        if (closeConfirmation.equals("y")) {
            System.out.println("\nPlease select the type of account to close: ");
            System.out.println("\nEnter '1' for checking or '2' for savings");
            int finalClose = scanner.nextInt();
            switch (finalClose) {
                case 1:
                    checkingAccount.setLength(0); // clears contents
                    checkingAccount.append("Account closed"); // adds new value
                    checkingBalance = 0.00; // deleted money
                    System.out.println("\nChecking account successfully closed.");
                    break;
                case 2:
                    savingsAccount.setLength(0);
                    savingsAccount.append("Account closed");
                    savingsBalance = 0.00;
                    System.out.println("\nSavings account successfully closed.");
                    break;
                default:
                    System.out.println("\nTransaction cancelled.");
                    break;
            }
        } else if (closeConfirmation.equals("n")) {
            System.out.println("\nTransaction cancelled.");
        }
    }

    protected void getAccountBalances() {
        System.out.println("\nAll account balances listed below: ");
        if (checkingBalance != 0) {
            System.out.println("\nChecking: " + checkingBalance);
        }
        if (savingsBalance != 0) {
            System.out.println("\nSavings: " + savingsBalance);
        }
    }
}