package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public SqlTracker() {
        init();
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        executeUpdateSQL("insert into items(name, created) values(\'"
                + item.getName() + "\', \'" + Timestamp.valueOf(item.getCreated()) + "\')");
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        return executeUpdateSQL(
                "update items "
                + "set name = '" + item.getName() + "', created = '"
                + Timestamp.valueOf(item.getCreated())
                + "' where id = " + id)
                != 0;
    }

    @Override
    public boolean delete(int id) {
        return executeUpdateSQL(
                "delete from items where id = " + String.valueOf(id)) != 0;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        ResultSet rs = executeQuery("select * from items");
        if (rs != null) {
            items = resultSetToList(rs);
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        ResultSet rs = executeQuery("select * from items where name = '" + key + "'");
        List<Item> items = new ArrayList<>();
        if (rs != null) {
            items = resultSetToList(rs);
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        ResultSet rs = executeQuery("select * from items where id = " + id);
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        if (rs != null) {
            items = resultSetToList(rs);
        }
        return items.size() > 0 ? items.get(0) : null;
    }

    private int executeUpdateSQL(String sql) {
        int rsl = 0;
        try (Statement statement = cn.createStatement()) {
            rsl = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private ResultSet executeQuery(String sql) {
        ResultSet resultSet = null;
        try (Statement statement = cn.createStatement()) {
             resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    private List<Item> resultSetToList(ResultSet rs) {
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        try {
            if (rs != null) {
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        if (i == 1) {
                            item.setId(rs.getInt(i));
                        } else if (i == 2) {
                            item.setName(rs.getString(i));
                        } else if (i == 3) {
                            item.setCreated(rs.getTimestamp(i).toLocalDateTime());
                        }
                    }
                    items.add(item);
                    item = new Item();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
