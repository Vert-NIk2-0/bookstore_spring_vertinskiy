package com.belhard.bookstore.controller;

import com.belhard.bookstore.connection.impl.ConnectionManagerImpl;
import com.belhard.bookstore.connection.impl.PropertiesUtilImpl;
import com.belhard.bookstore.controller.impl.BookCommand;
import com.belhard.bookstore.controller.impl.BooksCommand;
import com.belhard.bookstore.controller.impl.ErrorCommand;
import com.belhard.bookstore.controller.impl.UserCommand;
import com.belhard.bookstore.controller.impl.UsersCommand;
import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.impl.BookDaoImpl;
import com.belhard.bookstore.dao.impl.UserDaoImpl;
import lombok.extern.log4j.Log4j2;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class CommandFactory implements Closeable {

    public static final CommandFactory INSTANCE = new CommandFactory();
    private final Map<String, Command> map;
    private final List<Closeable> closeableList;

    private CommandFactory() {
        map = new HashMap<>();
        closeableList = new ArrayList<>();

        ConnectionManagerImpl connectionManager = getConnectionManager();
        closeableList.add(connectionManager);

        BookDao bookDao = new BookDaoImpl(connectionManager);
        UserDao userDao = new UserDaoImpl(connectionManager);

        map.put("book", new BookCommand(bookDao));
        map.put("books", new BooksCommand(bookDao));
        map.put("user", new UserCommand(userDao));
        map.put("users", new UsersCommand(userDao));
        map.put("error", new ErrorCommand());
    }

    private static ConnectionManagerImpl getConnectionManager() {
        PropertiesUtilImpl propertiesUtilImpl = new PropertiesUtilImpl("/application.properties");
        String url = propertiesUtilImpl.get("db.url");
        String hostname = propertiesUtilImpl.get("db.username");
        String password = propertiesUtilImpl.get("db.password");
        int poolSize = Integer.parseInt(propertiesUtilImpl.get("db.poolSize"));
        String driver = propertiesUtilImpl.get("db.driver");
        return new ConnectionManagerImpl(url, hostname, password, poolSize, driver);
    }

    public Command get(String commandParam) {
        Command command = map.get(commandParam);
        if (command == null) {
            command  = map.get("error");
        }
        return command;
    }

    @Override
    public void close(){
        for (Closeable closeable : closeableList) {
            try {
                closeable.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }
}
