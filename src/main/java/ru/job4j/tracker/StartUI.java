package ru.job4j.tracker;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store tracker, List<UserAction> actions) throws SQLException {
        boolean run = true;
        while (run) {
            snowMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    public void snowMenu(List<UserAction> actions) {
        out.println("Menu:");
        for (int i = 0; i < actions.size(); i++) {
            out.println(i + ". " + actions.get(i).name());
        }

    }

    public static void main(String[] args) throws SQLException {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        Store tracker = new HBMTracker();
        List<UserAction> actions = new ArrayList<>(Arrays.asList(
                new CreateAction(output), new CreateItemXAction(output), new FindAllAction(output),
                new EditItemAction(output), new DeleteItemAction(output), new DeleteItemXAction(output),
                new FindItemById(output), new FindItemsByName(output),
                new CloseProgramAction(output)
        ));
        new StartUI(output).init(input, tracker, actions);
    }

    private static String loadSysEnvIfNullThenConfig(String sysEnv, String key, Properties config) {
        String value = System.getenv(sysEnv);
        if (value == null) {
            value = config.getProperty(key);
        }
        return value;
    }

    private static Connection loadConnection() throws ClassNotFoundException, SQLException {
        var config = new Properties();
        try (InputStream in = StartUI.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        String url = loadSysEnvIfNullThenConfig("JDBC_URL", "url", config);
        String username = loadSysEnvIfNullThenConfig("JDBC_USERNAME", "username", config);
        String password = loadSysEnvIfNullThenConfig("JDBC_PASSWORD", "password", config);
        String driver = loadSysEnvIfNullThenConfig("JDBC_DRIVER", "driver-class-name", config);
        System.out.println("url=" + url);
        Class.forName(driver);
        return DriverManager.getConnection(
                url, username, password
        );
    }
}
