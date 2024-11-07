package com.belhard.bookstore.controller;

import com.belhard.bookstore.connection.impl.ConnectionManagerImpl;
import com.belhard.bookstore.connection.impl.PropertiesUtilImpl;
import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.entity.Book;
import com.belhard.bookstore.dao.entity.User;
import com.belhard.bookstore.dao.impl.BookDaoImpl;
import com.belhard.bookstore.dao.impl.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users")
public class UsersController extends HttpServlet {
    private final UserDao userDao;

    public UsersController() {
        Logger logger = LogManager.getLogger(UsersController.class);
        PropertiesUtilImpl PROPERTIES_UTIL_IMPL = new PropertiesUtilImpl("/application.properties");
        String URL = PROPERTIES_UTIL_IMPL.get("db.url");
        String HOSTNAME = PROPERTIES_UTIL_IMPL.get("db.username");
        String PASSWORD = PROPERTIES_UTIL_IMPL.get("db.password");
        ConnectionManagerImpl connectionManager = new ConnectionManagerImpl(URL, HOSTNAME, PASSWORD, "org.postgresql.Driver");
        userDao = new UserDaoImpl(connectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = userDao.getAll();
        PrintWriter writer = resp.getWriter();


        writer.printf("""
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Users | bookstore</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f4f9;
                    margin: 0;
                    padding: 20px;
                }
                .container {
                    max-width: 1200px;
                    margin: 0 auto;
                    display: grid;
                    grid-template-columns: repeat(3, 1fr);
                    gap: 20px;
                }
                .book-card {
                    background-color: #fff;
                    padding: 20px;
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                    border-radius: 8px;
                }
                h1 {
                    color: #333;
                    font-size: 1.5em;
                }
                p {
                    color: #555;
                    margin: 8px 0;
                }
                p span {
                    font-weight: bold;
                }
                a {
                    text-decoration: none;
                    color: #1a73e8;
                }
                a:hover {
                    text-decoration: underline;
                }
            </style>
        </head>
        <body>
            <div class="container">
        """);

        for(User user : userList) {
            writer.printf("""
                <div class="book-card">
                    <h1><span>%s</span></h1>
                    <p><span>ID:</span> %s</p>
                    <p><a href="user?id=%s">%s</a></p>
                    <p><span>Phone Number:</span> %s</p>
                </div>
            """,
                    user.getLogin(),
                    user.getId(),
                    user.getId(),
                    user.getEmail(),
                    user.getPhoneNumber());
        }

        writer.printf("""
            </div>
        </body>
        </html>
        """);



    }
}
