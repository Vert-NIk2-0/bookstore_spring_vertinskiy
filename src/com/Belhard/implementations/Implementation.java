package com.Belhard.implementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Implementation {
    private static Connection connection = null;
    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    protected Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        PropertiesUtil.get(URL_KEY),
                        PropertiesUtil.get(USER_KEY),
                        PropertiesUtil.get(PASSWORD_KEY)
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return connection;
        } else {
            return connection;
        }
    }
}
