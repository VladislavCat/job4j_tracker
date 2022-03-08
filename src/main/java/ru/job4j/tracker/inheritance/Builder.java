package ru.job4j.tracker.inheritance;

public class Builder extends Engineer {
    private String fieldOfBuilding;

    public Builder(String fieldOfBuilding) {
        super("Сопромат");
        this.fieldOfBuilding = fieldOfBuilding;
    }

    public void buildTheTower() {
    }
}
