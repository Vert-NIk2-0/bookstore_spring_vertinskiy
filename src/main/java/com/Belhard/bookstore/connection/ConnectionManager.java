package com.belhard.bookstore.connection;

import java.sql.Connection;

public interface ConnectionManager {
    Connection getConnection();
}
