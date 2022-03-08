package ru.job4j.tracker.inheritance;

import java.util.SortedMap;

public class Programmer extends Engineer {
    private String programLang;

    public Programmer() {
        super("IT");
        programLang = "Java";
    }

    public void writeAwesomeProgram() {
        System.out.println("Write a program...");
        System.out.println("Compilation...");
        System.out.println("-----------");
        System.out.println("---BUILD FAILURE---");
        System.out.println("---3312 ERROR IN BUILD---");
    }
}
