package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(),
                new CloseProgramAction()
        };
        new StartUI().init(in, tracker, actions);
        Assert.assertEquals(tracker.findAll()[0].getName(), "Item name");
    }

    @Test
    public void whenEditedItem() {
        Input in = new StubInput(
                new String[] {"0", "1", "Edited item", "1"}
        );
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        UserAction[] userAction = {
                new EditItemAction(),
                new CloseProgramAction()
        };
        new StartUI().init(in, tracker, userAction);
        Assert.assertEquals(tracker.findAll()[0].getName(), "Edited item");
    }

    @Test
    public void whenDeleteItem() {
        Input in = new StubInput(
                new String[] {"0", "1", "1"}
        );
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        UserAction[] userAction = {
                new DeleteItemAction(),
                new CloseProgramAction()
        };
        new StartUI().init(in, tracker, userAction);
        Assert.assertNull(null, tracker.findById(1));
    }
}
