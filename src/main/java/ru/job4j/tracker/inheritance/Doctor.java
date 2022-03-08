package ru.job4j.tracker.inheritance;

public class Doctor extends Profession {
    private int numberOfPatientsInMount;

    public Doctor(int i) {
        super("Мед-вуз");
        this.numberOfPatientsInMount = i;
    }

    public void heal() {
    }
}
