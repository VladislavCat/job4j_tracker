package ru.job4j.tracker.ooa;

public final class Airbus extends Aircraft {
    private static final int COUNT_ENGINE = 2;

    private String name;

    public Airbus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printModel() {
        System.out.println("Модель самолета: " + name);
    }

    public void printCountEngine() {
        String o = name.equals("A380") ? "Количество двигателей: " + 4 : "Количество двигателе: " + COUNT_ENGINE;
        System.out.println(o);
    }

    @Override
    public String toString() {
        return "Airbus{"
                + "name='" + name + '\''
                + '}';
    }
}