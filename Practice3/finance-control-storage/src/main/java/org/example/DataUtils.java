package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

public class DataUtils {
    @Getter
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    @Getter
    private static double balance = 0 ;

    public static void putMoneyOnBalance(double amount){
        transactions.add(new Transaction(new Date(), amount, TransactionType.INCOME));
        balance += amount;
    }

    public static void getMoneyFromBalance(double amount){
        if(amount > balance)
            throw new IllegalArgumentException("The wanted withdrawal amount is bigger then the balance.");
        transactions.add(new Transaction(new Date(), amount, TransactionType.EXPENSE));
        balance -= amount;
    }

}