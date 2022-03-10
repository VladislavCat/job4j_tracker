package ru.job4j.tracker;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StartUI {

    public void init(Scanner scanner, Tracker tracker) {
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
            } else {
                System.out.println("Пользователь выбрал: " + select);
            }
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
        new StartUI().init(scanner, tracker);
    }
}
