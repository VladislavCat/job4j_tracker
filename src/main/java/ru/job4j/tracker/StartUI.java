package ru.job4j.tracker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StartUI {

    public void init(Scanner scanner, Tracker tracker) throws InterruptedException {
        boolean run = true;
        while (run) {
            snowMenu();
            System.out.println("Select: ");
            int select;
            try {
                select = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Введите именно число! ");
                continue;
            }
            if (select == 6) {
                run = false;
            } else if (select == 0) {
                System.out.println("---Create item---");
                System.out.println("Enter name: ");
                String name = scanner.nextLine();
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
                System.out.println("Enter id: ");
                int id;
                try {
                    id = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Id заявки состоит из чисел.");
                    continue;
                }
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                tracker.replace(id, item);
                System.out.println("Item replaced!");
            } else {
                System.out.println("Пользователь выбрал: " + select);
            }
            Thread.sleep(3000);
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
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        try {
            new StartUI().init(scanner, tracker);
        } catch (InterruptedException e) {
            System.out.println("Поток досрочно завершился, ибо я криворукий");
        }
    }
}
