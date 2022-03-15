package ru.job4j.tracker.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (value[i] != null && value[i].equals(key)) {
                rsl = Integer.parseInt(value[i]);
            }
        }
        if (rsl == -1) {
            throw new ElementNotFoundException("Элемент не найден в массиве");
        }
        return rsl;
    }

    public static void main(String[] args) {
        String[] array = {"1", "2", "3", "4"};
        try {
            indexOf(array, "5");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}