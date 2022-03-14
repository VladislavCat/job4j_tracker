package ru.job4j.tracker;

public class CloseProgramAction implements UserAction {
    @Override
    public String name() {
        return "Close program";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        return false;
    }
}
