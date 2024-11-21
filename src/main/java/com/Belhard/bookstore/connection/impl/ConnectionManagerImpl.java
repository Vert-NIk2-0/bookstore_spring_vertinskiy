package com.belhard.bookstore.connection.impl;

import com.belhard.bookstore.connection.ConnectionManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
@Component
public class ConnectionManagerImpl implements ConnectionManager, Closeable {
    private ConnectionPool connectionPool;

    @Autowired
    public ConnectionManagerImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
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
