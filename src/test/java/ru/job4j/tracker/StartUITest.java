package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StartUITest {

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        UserAction[] actions = new UserAction[]{
                new EditItemAction(out),
                new CloseProgramAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Exit program" + ln
                        + "--- Edit item ---" + ln
                        + "Заявка заменена" + ln
                        + "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Exit program" + ln
                        + "---Exit program---" + ln
        ));
    }

    @Test
    public void whenFindAllItemsTestOutput() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        Item two = tracker.add(new Item("test2"));
        Item three = tracker.add(new Item("test3"));
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        UserAction[] actions = {
                new FindAllAction(out),
                new CloseProgramAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                + "0. Find all items." + ln
                + "1. Exit program" + ln
                + "--- Show all items ---" + ln
                + one + ln + two + ln + three + ln + "Menu:" + ln
                + "0. Find all items." + ln
                + "1. Exit program" + ln
                + "---Exit program---" + ln
        ));
    }

    @Test
    public void whenFindByNameItemsTestOutput() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("Err"));
        Item two = tracker.add(new Item("Sell"));
        Item three = tracker.add(new Item("A"));
        Item four = tracker.add(new Item("Sell"));
        Input in = new StubInput(
                new String[] {"0", "Sell", "1"}
        );
        UserAction[] actions = {
                new FindItemsByName(out),
                new CloseProgramAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Find items by name" + ln
                        + "1. Exit program" + ln
                        + "--- Find items by name ---" + ln
                        + two + ln + four + ln + "Menu:" + ln
                        + "0. Find items by name" + ln
                        + "1. Exit program" + ln
                        + "---Exit program---" + ln
        ));
    }

    @Test
    public void whenFindByIdItemsTestOutput() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("Err"));
        Item two = tracker.add(new Item("Sell"));
        Input in = new StubInput(
                new String[] {"0", "2", "1"}
        );
        UserAction[] actions = {
                new FindItemById(out),
                new CloseProgramAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Find item by id." + ln
                        + "1. Exit program" + ln
                        + "--- Find item by id ---" + ln
                        + two + ln + "Menu:" + ln
                        + "0. Find item by id." + ln
                        + "1. Exit program" + ln
                        + "---Exit program---" + ln
        ));
    }
}
