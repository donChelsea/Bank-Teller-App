package org.pursuit;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        startBank();
    }

    public static void startBank() {
        BankTeller bankTeller = new BankTeller();
        Customer customer = new Customer();
        int tellerOrCustomer;
        do {
            System.out.println("\nWelcome to Bank of Pursuit!\n");
            System.out.println("If you are a teller, please enter '1'.\nIf you are a customer, please enter '2'. \nEnter '3' to exit.");
            tellerOrCustomer = scanner.nextInt();
            if (tellerOrCustomer == 1) {
                bankTeller.startTellerApp();
            } else if (tellerOrCustomer == 2){
                customer.startCustomerApp();
            } else {
                System.out.println("\nThank you for banking with Bank of Pursuit. Goodbye!");
                break;
            }
        } while (tellerOrCustomer != 0);
    }
}
