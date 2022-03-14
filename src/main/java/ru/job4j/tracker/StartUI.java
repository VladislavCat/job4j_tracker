package ru.job4j.tracker;

public class StartUI {

    public void init(Input input, Tracker tracker) throws InterruptedException {
        boolean run = true;
        while (run) {
            snowMenu();
            int select = input.askInt("Select: ");
            if (select == 0) {
                System.out.println("---Create item---");
                String name = input.askStr("Enter name: ");
                Item item = new Item(name);
                tracker.add(item);
                System.out.println("Заявка добавлена: " + item);
            } else if (select == 1) {
                Item[] items = tracker.findAll();
                System.out.println("--- Show all items ---");
                if (items.length > 0) {
                    for (Item item : items) {
                        System.out.println(item);
                    }
                } else {
                    System.out.println("Хранилище еще не содержит заявок");
                }
            } else if (select == 2) {
                System.out.println("--- Edit item ---");
                int id = input.askInt("Enter id: ");
                String name = input.askStr("Enter name: ");
                Item item = new Item(name);
                if (tracker.replace(id, item)) {
                    System.out.println("Заявка заменена");
                } else {
                    System.out.println("Ошибка замены заявки");
                }
            } else if (select == 3) {
                System.out.println("--- Delete item ---");
                int id = input.askInt("Enter id item: ");
                if (tracker.delete(id)) {
                    System.out.println("Заявка удалена.");
                } else {
                    System.out.println("Ошибка удаления заявки.");
                }
            } else if (select == 4) {
                System.out.println("--- Find item by id ---");
                int id = input.askInt("Enter id: ");
                Item item = tracker.findById(id);
                if (item != null) {
                    System.out.println(item);
                } else {
                    System.out.println("Заявка под id " + id
                            + " не найдена.");
                }
            } else if (select == 5) {
                System.out.println("--- Find items by name ---");
                System.out.print("Enter name: ");
                String name = input.askStr("Enter name: ");
                Item[] items = tracker.findByName(name);
                if (items.length > 0) {
                    for (Item item : items) {
                        System.out.println(item);
                    }
                } else {
                    System.out.println("Заявок с именем " + name
                            + " не найдено");
                }
            } else if (select == 6) {
                run = false;
            }
            Thread.sleep(2500);
        }
    }

    public void snowMenu() {
        String[] menu = {
                "Add Item", "Snow All items",
                "Edit item", "Delete item", "Find item by id",
                "Find items by name", "Exit program"
        };
        System.out.println("Menu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }

    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        try {
            new StartUI().init(input, tracker);
        } catch (InterruptedException e) {
            System.out.println("Поток досрочно завершился, ибо я криворукий");
        }
    }
}
