package ru.job4j.tracker.inheritance;

public class Surgeon extends Doctor {
    private int numberOfOperationInMount;

    public Surgeon(int i) {
        super(99);
        numberOfOperationInMount = i;
    }

    public void operation() {
    }
}
