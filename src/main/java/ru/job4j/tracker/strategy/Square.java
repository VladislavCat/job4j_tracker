package ru.job4j.tracker.strategy;

public class Square implements Shape {
    @Override
    public String draw() {
        String ln = System.lineSeparator();
        return "-----" + ln
                + "|   |" + ln
                + "-----" + ln;
    }

    public static void main(String[] args) {
        Square a = new Square();
        System.out.println(a.draw());
    }
}
