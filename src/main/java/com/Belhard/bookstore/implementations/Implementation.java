package com.Belhard.bookstore.implementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Implementation {
    private static Connection connection = null;
    private static final String HOSTNAME_KEY = "db.hostname";
    private static final String PORT_KEY = "db.port";
    private static final String PATH_KEY = "db.path";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String URL = "jdbc:postgresql://"
            + PropertiesUtil.get(HOSTNAME_KEY) + ':'
            + PropertiesUtil.get(PORT_KEY)
            + PropertiesUtil.get(PATH_KEY)
            + "?user=" + PropertiesUtil.get(USERNAME_KEY)
            + "&password=" + PropertiesUtil.get(PASSWORD_KEY);

    protected Connection getConnection() {
        if (connection == null) {
            try {



                connection = DriverManager.getConnection(URL);
//                connection = DriverManager.getConnection(
//                        PropertiesUtil.get(URL_KEY),
//                        PropertiesUtil.get(USER_KEY),
//                        PropertiesUtil.get(PASSWORD_KEY)
//                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return connection;
        } else {
            return connection;
        }
    }
}
