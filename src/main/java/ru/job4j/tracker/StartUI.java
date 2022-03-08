package ru.job4j.tracker;

import java.time.format.DateTimeFormatter;

public class StartUI {

    public static void main(String[] args) {
        Item item = new Item();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-EEEE-yyyy HH:mm:ss");
        String currentDateFormat = item.getCreated().format(formatter);
        System.out.println("Дата и время: " + currentDateFormat);
    }
}
