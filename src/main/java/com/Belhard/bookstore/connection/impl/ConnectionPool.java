package com.belhard.bookstore.connection.impl;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Log4j2
public class ConnectionPool {
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final int poolSize;

    public ConnectionPool(String driver, String url, String user, String password, int poolSize) {
        this.poolSize = poolSize;
        this.freeConnections = new LinkedBlockingDeque<>();
        try {
            Class.forName(driver);
            log.info("Database driver loaded");
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                freeConnections.add(new ProxyConnection(connection, this));
                log.info("Connection created");
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection proxy) {
            freeConnections.add(proxy);
        } else {
            log.warn("Returned not proxy connection");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < poolSize; i++) {
            try {
                ProxyConnection connection = freeConnections.take();
                connection.reallyClose();
                log.info("Connection closed");
            } catch (InterruptedException | SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
        deregisterDrivers();
    }

    public void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                log.info("Driver={} deregistered", driver);
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        });
    }
}
