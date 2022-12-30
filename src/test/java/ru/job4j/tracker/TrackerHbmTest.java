package ru.job4j.tracker;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class TrackerHbmTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HBMTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            Assertions.assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    public void whenReplaceItem() throws Exception {
        try (var tracker = new HBMTracker()) {
            Item item = new Item("Original item");
            tracker.add(item);
            Item itemReplace = new Item("Replace Item");
            itemReplace.setId(item.getId());
            tracker.replace(item.getId(), itemReplace);
            Assertions.assertThat(tracker.findById(item.getId()))
                    .isEqualTo(itemReplace);
        }
    }

    @Test
    public void whenDeleteItem() throws Exception {
        try (var tracker = new HBMTracker()) {
            Item item = new Item("Deleted item");
            tracker.add(item);
            tracker.delete(item.getId());
            Assertions.assertThat(tracker.findById(item.getId())).isNull();
        }
    }

    @Test
    public void whenFindAll() throws Exception {
        try (var tracker = new HBMTracker()) {
            Item item1 = new Item("Item 1");
            Item item2 = new Item("Item 2");
            Item item3 = new Item("Item 3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            Assertions.assertThat(List.of(item1, item2, item3)).isEqualTo(tracker.findAll());
        }
    }

    @Test
    public void whenFindByName() throws Exception {
        try (var tracker = new HBMTracker()) {
            Item item1 = new Item("Task 1");
            Item item2 = new Item("Task 2");
            Item item3 = new Item("Task 3");
            Item item4 = new Item("Task 2");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            tracker.add(item4);
            Assertions.assertThat(List.of(item2, item4)).isEqualTo(tracker.findByName("Task 2"));
        }
    }
}
