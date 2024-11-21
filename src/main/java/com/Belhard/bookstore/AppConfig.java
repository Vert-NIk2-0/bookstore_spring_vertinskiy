package com.belhard.bookstore;

import com.belhard.bookstore.connection.ConnectionManager;
import com.belhard.bookstore.connection.impl.ConnectionManagerImpl;
import com.belhard.bookstore.connection.impl.ConnectionPool;
import com.belhard.bookstore.controller.impl.BookCommand;
import com.belhard.bookstore.controller.impl.BooksCommand;
import com.belhard.bookstore.controller.impl.ErrorCommand;
import com.belhard.bookstore.controller.impl.UserCommand;
import com.belhard.bookstore.controller.impl.UsersCommand;
import com.belhard.bookstore.dao.impl.BookDaoImpl;
import com.belhard.bookstore.dao.impl.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ConnectionPool connectionPool() {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://frenziedly-regal-tench.data-1.use1.tembo.io:5432/postgres";
        String user = "postgres";
        String password = "l9Wkdb6ewOvNixvR";
        int poolSize = 5;
        return new ConnectionPool(driver, url, user, password, poolSize);
    }

    @Bean
    public ConnectionManagerImpl connectionManager(ConnectionPool connectionPool) {
        return new ConnectionManagerImpl(connectionPool);
    }

    @Bean
    public UserDaoImpl userDao (ConnectionManagerImpl connectionManager) {
        return new UserDaoImpl(connectionManager);
    }

    @Bean
    public BookDaoImpl bookDao (ConnectionManagerImpl connectionManager) {
        return new BookDaoImpl(connectionManager);
    }

    @Bean
    public BookCommand book (BookDaoImpl bookDao) {
        return new BookCommand(bookDao);
    }

    @Bean
    public BooksCommand books (BookDaoImpl bookDao) {
        return new BooksCommand(bookDao);
    }

    @Bean
    public UserCommand user (UserDaoImpl userDao) {
        return new UserCommand(userDao);
    }

    @Bean
    public UsersCommand users (UserDaoImpl userDao) {
        return new UsersCommand(userDao);
    }

    @Bean
    public ErrorCommand error () {
        return new ErrorCommand();
    }
}
