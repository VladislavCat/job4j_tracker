package ru.job4j.tracker.oop;

public class Battery {
    private int load;

    public Battery(int load) {
        this.load = load;
    }

    public int getLoad() {
        return load;
    }

    public void exchange(Battery another) {
        another.load += this.getLoad();
        this.load = 0;
    }

    public static void main(String[] args) {

    }
}