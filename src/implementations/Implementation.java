package implementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Implementation {
    private static Connection connection = null;
    private static final String URL = "jdbc:postgresql://localhost:5432/bookstore_bh";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    protected Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return connection;
        } else {
            return connection;
        }
    }
}
