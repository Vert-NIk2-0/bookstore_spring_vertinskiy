package com.belhard.bookstore.connection.impl;

import com.belhard.bookstore.connection.ConnectionManager;
import lombok.extern.log4j.Log4j2;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
public class ConnectionManagerImpl implements ConnectionManager, Closeable {
    private ConnectionPool connectionPool;

    public ConnectionManagerImpl(String url, String username, String password, int poolSize, String driver) {
        connectionPool = new ConnectionPool(driver, url, username, password, poolSize);
        log.info("Connection pool initialized");
    }

    @Override
    public Connection getConnection() {
        return connectionPool.getConnection();
    }

    @Override
    public void close() throws IOException {
        if (connectionPool != null) {
            connectionPool.destroyPool();
            connectionPool = null;
            log.info("Connection pull destroyed");
        }
    }
}
