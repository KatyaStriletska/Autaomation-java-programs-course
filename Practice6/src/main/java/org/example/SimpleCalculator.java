package org.example;

public class SimpleCalculator {
    public int multiplication(int a, int b) {
        return a * b;
    }
    public int division(int a, int b) {
        if(b == 0) throw new IllegalArgumentException("Second number must be more then 0!");
        return a / b;
    }
}