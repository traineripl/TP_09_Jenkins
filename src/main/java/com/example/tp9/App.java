package com.example.tp9;

public class App {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("2 + 3 = " + calculator.add(2, 3));
        System.out.println("8 / 2 = " + calculator.divide(8, 2));
    }
}
