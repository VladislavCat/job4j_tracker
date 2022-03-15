package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class StartUITest {

    @Ignore
    public void whenCreateItem() {
        Output output = new ConsoleOutput();
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(output),
                new CloseProgramAction(output)
        };
        new StartUI(output).init(in, tracker, actions);
        Assert.assertEquals(tracker.findAll()[0].getName(), "Item name");
    }

    @Ignore
    public void whenEditedItem() {
        Output output = new ConsoleOutput();
        Input in = new StubInput(
                new String[] {"0", "1", "Edited item", "1"}
        );
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        UserAction[] userAction = {
                new EditItemAction(output),
                new CloseProgramAction(output)
        };
        new StartUI(output).init(in, tracker, userAction);
        Assert.assertEquals(tracker.findAll()[0].getName(), "Edited item");
    }

    @Ignore
    public void whenDeleteItem() {
        Output output = new ConsoleOutput();
        Input in = new StubInput(
                new String[] {"0", "1", "1"}
        );
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        UserAction[] userAction = {
                new DeleteItemAction(output),
                new CloseProgramAction(output)
        };
        new StartUI(output).init(in, tracker, userAction);
        Assert.assertNull(null, tracker.findById(1));
    }
}
