package ru.job4j.tracker;

public class StartUI {

    public void init(Input input, Tracker tracker, UserAction[] actions) throws InterruptedException {
        boolean run = true;
        while (run) {
            snowMenu(actions);
            int select = input.askInt("Select: ");
            UserAction action = actions[select];
            run = action.execute(input, tracker);
            Thread.sleep(1500);
        }
    }

    public void snowMenu(UserAction[] actions) {
        System.out.println("Menu:");
        for (int i = 0; i < actions.length; i++) {
            System.out.println(i + ". " + actions[i].name());
        }

    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(), new FindAllAction(),
                new EditItemAction(), new DeleteItemAction(),
                new FindItemById(), new FindItemsByName(),
                new CloseProgramAction()
        };
        try {
            new StartUI().init(input, tracker, actions);
        } catch (InterruptedException e) {
            System.out.println("Поток досрочно завершился, ибо я криворукий");
        }
    }
}
