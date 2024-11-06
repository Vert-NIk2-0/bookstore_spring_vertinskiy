package com.belhard.bookstore.connection.impl;

import com.belhard.bookstore.connection.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerImpl implements ConnectionManager {
    private static final Logger logger = LogManager.getLogger(ConnectionManagerImpl.class);
    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    public ConnectionManagerImpl(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public Connection getConnection() {
        Connection connection;
        try {
            logger.info("Creating database connection");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        logger.debug("Accessing the database: {}", connection);
        return connection;
    }
}
