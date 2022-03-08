package ru.job4j.tracker.inheritance;

public class Engineer extends Profession {
    private String fieldOfScience;

    public Engineer(String fieldOfScience) {
        super("Физ-мат");
        this.fieldOfScience = fieldOfScience;
    }

    public void calculation() {
    }
}
