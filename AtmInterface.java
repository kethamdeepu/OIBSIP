// package com.atm;

import java.util.ArrayList;
import java.util.Scanner;

public class AtmInterface {


    public static class ATM {
        private final Bank bank;
        private final Scanner scanner;

        public ATM() {
            this.bank = new Bank();
            this.scanner = new Scanner(System.in);
        }

        public boolean login() {
            System.out.print("User ID: ");
            String userID = scanner.nextLine();
            System.out.print("User PIN: ");
            String userPIN = scanner.nextLine();
            return bank.authenticateUser(userID, userPIN);
        }


        public void showTransactionHistory() {
            User currentUser = bank.getCurrentUser();
            System.out.println("Transaction history for " + currentUser.getFullName() + ":");
            for (String transaction : currentUser.getTransactionHistory()) {
                System.out.println(transaction);
            }
        }


        public void doWithdraw() {
            User currentUser = bank.getCurrentUser();
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            if (currentUser.withdraw(amount)) {
                System.out.println("Withdrawal successful, new balance: Rs." + currentUser.getAccountBalance());
            } else {
                System.out.println("Insufficient funds");
            }
        }


        public void doDeposit() {
            User currentUser = bank.getCurrentUser();
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            currentUser.deposit(amount);
            System.out.println("Deposit successful, new balance: Rs." + currentUser.getAccountBalance());
        }


        public void doTransfer() {
            User currentUser = bank.getCurrentUser();

            System.out.print("Enter Beneficiary user ID: ");
            String recipientID = scanner.nextLine();
            User recipient = bank.getUserByID(recipientID);
            if (recipient == null) {
                System.out.println("Recipient not found.");
                return;
            }


            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();
            if (currentUser.transfer(amount, recipient)) {
                System.out.println("Transfer successful, new balance:Rs. " + currentUser.getAccountBalance());
            } else {
                System.out.println("Insufficient funds");
            }

        }


    }

    public static class Bank {

        private final ArrayList<User> users;
        private User currentUser;

        public Bank() {
            this.users = new ArrayList<>();
            this.currentUser = null;
            // adding  some initial  users for testing
            users.add(new User("Nag", "7434", "Nagendra BaBu", "Karakoti", 1000.0));
            users.add(new User("Chinnodu", "5268", "Bhadri", "Karakoti", 1000000.0));

        }

        public boolean authenticateUser(String userID, String userPIN) {
            for (User user : users) {
                if (user.getUserID().equals(userID) && user.authenticate(userPIN)) {
                    currentUser = user;
                    return true;
                }
            }
            return false;
        }

        public User getCurrentUser() {
            return currentUser;
        }

        public User getUserByID(String userID) {
            for (User user : users) {
                if (user.getUserID().equals(userID)) {
                    return user;
                }
            }
            return null;
        }
    }

    public static class User {

        private final String userID;
        private final String userPIN;
        private final String firstName;
        private final String lastName;
        private double accountBalance;
        private final ArrayList<String> transactionHistory;

        public User(String userID, String userPIN, String firstName, String lastName, double initialBalance) {
            this.userID = userID;
            this.userPIN = userPIN;
            this.firstName = firstName;
            this.lastName = lastName;
            this.accountBalance = initialBalance;
            this.transactionHistory = new ArrayList<>();
        }

        public boolean authenticate(String userPIN) {
            return this.userPIN.equals(userPIN);
        }

        public boolean withdraw(double amount) {
            if (amount > accountBalance) {
                return false;
            } else {
                accountBalance -= amount;
                String transaction = "Withdrawal: -Rs." + amount + " New balance: Rs." + accountBalance;
                transactionHistory.add(transaction);
                return true;
            }
        }

        public void deposit(double amount) {
            accountBalance += amount;
            String transaction = "Deposit: +Rs." + amount + " New balance: Rs." + accountBalance;
            transactionHistory.add(transaction);
        }

        public boolean transfer(double amount, User recipient) {
            if (amount <= accountBalance) {
                accountBalance -= amount;
                recipient.deposit(amount);
                transactionHistory.add("Transfer of Rs." + amount + " to user " + recipient.getUserID());
                return true;
            } else {
                return false;
            }
        }

        public String getUserID() {
            return userID;
        }


        public double getAccountBalance() {
            return accountBalance;
        }

        public ArrayList<String> getTransactionHistory() {
            return transactionHistory;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the SBI ATM");
        System.out.println("PLEASE INSERT YOUR CARD");

        ATM atm = new ATM();


            if (atm.login()) {
                while (true) {

                    System.out.println("Choose an option:");
                    System.out.println("1. View transaction history");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Deposit");
                    System.out.println("4. Transfer");
                    System.out.println("5. Set PIN");
                    System.out.println("6. Quit");
                    System.out.print("Choice: ");

                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1 -> atm.showTransactionHistory();
                        case 2 -> atm.doWithdraw();
                        case 3 -> atm.doDeposit();
                        case 4 -> atm.doTransfer();
                        case 6 -> {
                            System.out.println("Thank You Visit Again.");
                            return;
                        }
                        default -> System.out.println("Invalid choice, please try again");
                    }
                }
            }



    }
}