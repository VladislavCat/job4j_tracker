package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HBMTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        int i = 0;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            i = session.createQuery("Update Item set name = :fName where id = :fId")
                    .setParameter("fName", item.getName()).setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i != 0;
    }

    @Override
    public boolean delete(int id) {
        int i = 0;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            i = session.createQuery("delete Item where id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i != 0;
    }

    @Override
    public List<Item> findAll() {
        List<Item> itemList = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query<Item> query = session.createQuery("from Item", Item.class);
            itemList.addAll(query.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> itemList = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query<Item> query = session.createQuery("from Item where name like :fName", Item.class)
                    .setParameter("fName", key);
            itemList.addAll(query.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item findById(int id) {
        Item item;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query<Item> query = session.createQuery("from Item where id = :fId", Item.class)
                    .setParameter("fId", id);
            item = query.uniqueResult();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
    }
}
