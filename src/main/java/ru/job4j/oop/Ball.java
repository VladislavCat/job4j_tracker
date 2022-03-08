package ru.job4j.oop;

public class Ball {

    public void tryRun(boolean bool, String name) {
        String s = bool ? "Колобок съеден " + name
                : "Колобок сбежал от " + name;
        System.out.println(s);
    }

}
