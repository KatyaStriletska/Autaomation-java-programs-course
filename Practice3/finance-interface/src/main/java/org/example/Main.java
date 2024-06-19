package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello user!");
        Scanner scanner = new Scanner(System.in);
        int input = 0;

        while (input != 5) {
            System.out.println("Enter what you want to do: " +
                    "\n1 - put money on balance " +
                    "\n2 - get money from balance " +
                    "\n3 - check balance" +
                    "\n4 - see all transaction's history" +
                    "\n5 - exit");
            input = scanner.nextInt();

            double amount = 0;
            switch (input) {
                case 1:
                    System.out.println("Enter amount to put on balance:");
                    amount = scanner.nextDouble();
                    DataUtils.putMoneyOnBalance(amount);
                    System.out.println(amount + " successfully put on bank account.");
                    break;
                case 2:
                    System.out.println("Enter amount to get from balance:");
                    amount = scanner.nextDouble();
                    try {
                        DataUtils.getMoneyFromBalance(amount);
                        System.out.println(amount + " successfully got from bank account.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Your balance = " + DataUtils.getBalance());
                    break;
                case 4:
                    System.out.println("Your transaction history = \n");
                    for (Transaction t: DataUtils.getTransactions())
                        System.out.println(t.toString());

                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }

        scanner.close();
    }
}
