package ru.job4j.tracker.inheritance;

public class Doctor extends Profession {
    private int numberOfPatientsInMount;

    public Doctor(String name, String surname, String educ, String birthday,
                  int i) {
        super(name, surname, educ, birthday);
        this.numberOfPatientsInMount = i;
    }

    public void heal() {
    }
}
