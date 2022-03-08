package ru.job4j.oop;

public class Error {
    boolean active;
    int status;
    String message;

    public Error() {
    }

    public Error(Boolean bool, int i, String str) {
        active = bool;
        status = i;
        message = str;
    }

    public void printInfo() {
        System.out.println(active);
        System.out.println(status);
        System.out.println(message);
        System.out.println("-------");
    }

    public static void main(String[] args) {
        Error e1 = new Error();
        Error e2 = new Error(true, 1115, "NPE");
        e1.printInfo();
        e2.printInfo();
    }

}
