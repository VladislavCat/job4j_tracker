package ru.job4j.tracker.search;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PhoneDictionaryTest {

    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        ArrayList<Person> persons = phones.find("Petr");
        Assert.assertEquals(persons.get(0).getSurname(), "Arsentev");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenFindFalse() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        ArrayList<Person> persons = phones.find("Alex");
        String name = persons.get(0).getName();
    }

    @Test
    public void whenTwoPersonName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Alex", "Semenov", "534872", "New York")
        );
        phones.add(
                new Person("York", "Gryn", "0010100", "Altay")
        );
        ArrayList<Person> persons = phones.find("York");
        Assert.assertEquals(persons.get(1).getAddress(), "Altay");
    }

}
