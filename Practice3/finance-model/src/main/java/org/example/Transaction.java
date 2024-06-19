package org.example;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;
@Getter
@ToString
public class Transaction {
    private Date incomeDate;
    private double sum;
    private TransactionType type;

    public Transaction(Date incomeDate, double sum, TransactionType type) {
        this.incomeDate = incomeDate;
        this.sum = sum;
        this.type = type;
    }
}