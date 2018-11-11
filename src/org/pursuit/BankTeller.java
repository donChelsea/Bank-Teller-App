package org.pursuit;

import java.util.*;

public class BankTeller {
    Scanner scanner = new Scanner(System.in);
    HashSet <StringBuilder> allBankCustomers = new HashSet<>();
    HashMap <StringBuilder, Double> allCheckingAccountsMap = new HashMap<>();
    HashMap <StringBuilder, Double> allSavingsAccountsMap = new HashMap<>();

    public BankTeller() {}

    public void startTellerApp() {
        createDummyAccounts();
        System.out.println("\nWelcome to the Team Member portal!");
        tellerAppMenu();
    }

    protected void createNewCustomer() {
        StringBuilder newCustomerName = new StringBuilder();
        System.out.println("\nTo create a new customer, please answer the following questions: \n");
        System.out.println("What is the customer's first and last name?\n");
        scanner.nextLine();
        newCustomerName.append(scanner.nextLine());
        allBankCustomers.add(newCustomerName);
        System.out.println("\nNew customer " + newCustomerName + " has been successfully registered.");
    }

    protected void tellerAppMenu() {
        int tellerMenuChoice;
        do {
            System.out.println("\nWhat would you like to do? Please select one of the following options: \n");
            System.out.println("1. Sign in");
            System.out.println("2. Create a new customer");
            System.out.println("3. View all customers");
            System.out.println("4. View all accounts");
            System.out.println("5. Delete an account");
            System.out.println("6. Sign out");
            tellerMenuChoice = scanner.nextInt();
            switch (tellerMenuChoice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    createNewCustomer();
                    break;
                case 3:
                    getAllBankCustomers();
                    break;
                case 4:
                    getAllAccounts(); // needs work
                    break;
                case 5:
                    deleteAccountData();
                    System.out.println(allBankCustomers);
                    break;
                case 6:
                    signOut();
                    break;
            }
        } while (tellerMenuChoice != 6);
    }

    protected void signIn() {
        System.out.println("Please enter your username: ");
        String tellerUserID = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Please enter your password: ");
        String tellerPassword = scanner.nextLine();
        System.out.println("\nWelcome! You're successfully signed in.\n");
    }

    protected void signOut() {
        System.out.println("\nThank you for being a valued team member! Goodbye!");
    }

    protected void getAllAccounts() {
        System.out.println("\nAll checking accounts: \n");
        for (Map.Entry<StringBuilder, Double> entry : allCheckingAccountsMap.entrySet()) {
            if (entry.getValue() != 0) {
                System.out.println(entry);
            }
        }
        System.out.println("\nAll savings accounts: \n");
        for (Map.Entry<StringBuilder, Double> entry : allSavingsAccountsMap.entrySet()) {
            if (entry.getValue() != 0) {
                System.out.println(entry);
            }
        }
    }

    protected void getAllBankCustomers() {
        System.out.println("\nAll customers of Bank of Pursuit listed below: ");
        System.out.println(allBankCustomers);
    }

    protected void deleteAccountData() {
        System.out.println("What is the name of the account holder for the account to delete?");
        StringBuilder deleteThisAccount = new StringBuilder().append(scanner.nextLine().toLowerCase());
        scanner.nextLine();
        StringBuilder checkForThisAccount = new StringBuilder().append("Account closed");
        for (StringBuilder customer : allBankCustomers) {
            if (customer == deleteThisAccount || customer == checkForThisAccount) {
                customer.delete(0, customer.length());
                System.out.println("Account data successfully deleted.");
            } else {
                System.out.println("No name found in records.");
            }
        }
        for (Map.Entry entry : allCheckingAccountsMap.entrySet()) {
            StringBuilder account = (StringBuilder) entry.getKey();
            double balance = (double) entry.getValue();
            if ((account == checkForThisAccount || account == deleteThisAccount) && balance == 0.00) {
                allCheckingAccountsMap.remove(account, balance);
                System.out.println("Account data successfully deleted.");
            } else {
                System.out.println("No name found in records.");
            }
        }
        for (Map.Entry entry : allSavingsAccountsMap.entrySet()) {
            StringBuilder account = (StringBuilder) entry.getKey();
            double balance = (double) entry.getValue();
            if ((account == checkForThisAccount || account == deleteThisAccount) && balance == 0.00) {
                System.out.println("Account data successfully deleted.");
            } else {
                System.out.println("No name found in records.");
            }
        }
    }

    private void createDummyAccounts() {
        StringBuilder customer1 = new StringBuilder().append("Michael Scott");
        StringBuilder customer2 = new StringBuilder().append("Frank Underwood");
        StringBuilder customer3 = new StringBuilder().append("Claire Underwood");
        StringBuilder customer4 = new StringBuilder().append("Annalise Keating");
        StringBuilder customer5 = new StringBuilder().append("Black Widow");
        allBankCustomers.add(customer1);
        allBankCustomers.add(customer2);
        allBankCustomers.add(customer3);
        allBankCustomers.add(customer4);
        allBankCustomers.add(customer5);
        allCheckingAccountsMap.put(customer1, 0.00);
        allCheckingAccountsMap.put(customer2, 5000.00);
        allCheckingAccountsMap.put(customer3, 70000.00);
        allCheckingAccountsMap.put(customer4, 0.00);
        allCheckingAccountsMap.put(customer5, 30.00);
        allSavingsAccountsMap.put(customer1, 100.00);
        allSavingsAccountsMap.put(customer2, 0.00);
        allSavingsAccountsMap.put(customer3, -20.00);
        allSavingsAccountsMap.put(customer4, 30000.00);
        allSavingsAccountsMap.put(customer5, 0.00);
    }
}
