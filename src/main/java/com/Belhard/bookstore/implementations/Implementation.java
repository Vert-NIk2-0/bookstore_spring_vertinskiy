package com.Belhard.bookstore.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Implementation {
    private static Connection connection = null;
    private static final Logger logger = LogManager.getLogger(Implementation.class);
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
                logger.info("Creating database connection");
                connection = DriverManager.getConnection(URL);
            } catch (SQLException e) {
                logger.error("Failed to create database connection");
                throw new RuntimeException(e);
            }
        }
        logger.debug("Accessing the database: {}", connection);
        return connection;
    }
}
