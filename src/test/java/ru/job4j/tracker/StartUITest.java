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
        UserAction createItem = new CreateAction();
        createItem.execute(input, tracker);
        Item expected = new Item("Fix PC");
        Item created = tracker.findAll()[0];
        Assert.assertEquals(created.getName(), expected.getName());
    }

    @Test
    public void whenEditItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                String.valueOf(item.getId()),
                "edited item"
        };
        UserAction editItem = new EditItemAction();
        editItem.execute(new StubInput(answers), tracker);
        Item edited = tracker.findById(item.getId());
        Assert.assertEquals(edited.getName(), "edited item");
    }

    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("new item");
        tracker.add(item);
        UserAction delete = new DeleteItemAction();
        String[] answers = {String.valueOf(item.getId())};
        delete.execute(new StubInput(answers), tracker);
        Assert.assertNull(null, tracker.findById(item.getId()));
    }
}
