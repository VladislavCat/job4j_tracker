package ru.job4j.tracker.inheritance;

public class Dantist extends Doctor {
    private int numberOfRemovedTeeth;

    public Dantist(int i) {
        super(18);
        numberOfRemovedTeeth = i;
    }

    public void enablingDrill() {
    }
}
