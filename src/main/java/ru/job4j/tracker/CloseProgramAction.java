package ru.job4j.tracker;

public class CloseProgramAction implements UserAction {
    private final Output out;

    public CloseProgramAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Exit program";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("---Exit program---");
        return false;
    }
}
