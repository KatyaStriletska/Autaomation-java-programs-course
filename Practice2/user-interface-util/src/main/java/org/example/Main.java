package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.example.UsersDataBase.users;

public class Main {
    public static void main(String[] args) throws IOException {
        UserService.fillUsers();

        while (true) {
            System.out.println("Hello! Have you already signed up? Y/N and E - if you want to exit:");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if (choice.equals("E")) {
                System.out.println("Thank you for work!");
                return;
            }
            while (!choice.equals("Y") && !choice.equals("N")) {
                System.out.println("Please enter Y/N only!");
                choice = scanner.nextLine();
            }

            if (choice.equals("Y")) {
                System.out.println("Good! So Please enter your login and password to log in: ");
                System.out.println("Login : ");
                String login = scanner.nextLine();
                System.out.println("Password : ");
                String password = scanner.nextLine();
                if (UserService.containsUser(login, password)) {
                    System.out.println("You have successfully logged in! \nYour profile:");
                    System.out.println(UsersDataBase.getUserByLogin(login));
                } else {
                    System.out.println("You aren't registered :(");
                }
            } else {
                System.out.println("Let's make you a profile! Input your data: ");
                System.out.println("Login: ");
                String login = scanner.nextLine();
                System.out.println("Password: ");
                String password = scanner.nextLine();
                System.out.println("Name: ");
                String name = scanner.nextLine();
                System.out.println("Age: ");
                int age = scanner.nextInt();

                while (!UserService.addNewUser(login, password, age, name)) {
                    System.out.println("Ups... Such login is already exist try another: ");
                    System.out.println("Login");
                    login = scanner.nextLine();
                }
                ;
                System.out.println("Congratulation! You have successfully signed up!");
            }

        }
    }
}