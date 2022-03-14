package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;


import static org.hamcrest.core.Is.is;

public class StartUITest {

    @Test
    public void whenAddItem() {
        String[] answers = {"Fix PC"};
        Tracker tracker = new Tracker();
        Input input = new StubInput(answers);
        StartUI.createItem(input, tracker);
        Item expected = new Item("Fix PC");
        Item created = tracker.findAll()[0];
        Assert.assertEquals(created.getName(), expected.getName());
    }
}
