package ru.job4j.tracker.ex;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        System.out.println(message);
    }
}
