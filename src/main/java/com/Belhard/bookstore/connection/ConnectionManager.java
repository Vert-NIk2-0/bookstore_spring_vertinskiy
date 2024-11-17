package com.belhard.bookstore.connection;

import java.io.Closeable;
import java.sql.Connection;

public interface ConnectionManager extends Closeable {
    Connection getConnection();
}
