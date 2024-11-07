package com.belhard.bookstore.controller;

import com.belhard.bookstore.connection.impl.ConnectionManagerImpl;
import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.entity.User;
import com.belhard.bookstore.dao.impl.UserDaoImpl;
import com.belhard.bookstore.connection.impl.PropertiesUtilImpl;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.impl.UserDtoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private final UserDao userDao;

    public UserController() {
        Logger logger = LogManager.getLogger(UserController.class);
        PropertiesUtilImpl PROPERTIES_UTIL_IMPL = new PropertiesUtilImpl("/application.properties");
        String URL = PROPERTIES_UTIL_IMPL.get("db.url");
        String HOSTNAME = PROPERTIES_UTIL_IMPL.get("db.username");
        String PASSWORD = PROPERTIES_UTIL_IMPL.get("db.password");
        ConnectionManagerImpl connectionManager = new ConnectionManagerImpl(URL, HOSTNAME, PASSWORD, "org.postgresql.Driver");
        userDao = new UserDaoImpl(connectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqId = req.getParameter("id");
        long id;
        User user;
        try {
            id = Long.parseLong(reqId);
        } catch (RuntimeException e) {
            showErrorPage(resp);
            return;
        }
        user = userDao.getById(id);
        if (user == null) {
            showErrorPage(resp);
            return;
        }

        PrintWriter writer = resp.getWriter();
        writer.printf("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>%s | bookstore</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f4f9;
                            margin: 0;
                            padding: 20px;
                        }
                        .container {
                            max-width: 600px;
                            margin: 0 auto;
                            background-color: #fff;
                            padding: 20px;
                            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                            border-radius: 8px;
                        }
                        h1 {
                            color: #333;
                        }
                        p {
                            color: #555;
                            margin: 8px 0;
                        }
                        p span {
                            font-weight: bold;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>%s</h1>
                        <p><span>%s %s</span></p>
                        <p><span>%s</span></p>
                        <p><span>Phone number:</span> %s</p>
                        <p><span>Date of birth:</span> %s</p>
                        <p><span>Gender:</span> %s</p>
                        <p><span>Role:</span> %s</p>
                    </div>
                </body>
                </html>
                
                """,
                user.getLogin(),
                user.getLogin(),
                user.getLastName(),
                user.getFirstName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getRole());
    }

    private void showErrorPage(HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.printf("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Error - Invalid ID</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f4f4f9;
                            margin: 0;
                            padding: 0;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            height: 100vh;
                        }
                        .error-container {
                            text-align: center;
                            background-color: #fff;
                            padding: 30px;
                            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                            border-radius: 8px;
                        }
                        .error-container h1 {
                            color: #e74c3c;
                            font-size: 2em;
                        }
                        .error-container p {
                            color: #555;
                            margin: 10px 0;
                        }
                    </style>
                </head>
                <body>
                    <div class="error-container">
                        <h1>Oops! Invalid ID</h1>
                        <p>It seems like the ID you provided is not valid.</p>
                        <p>Please check the ID and try again.</p>
                    </div>
                </body>
                </html>
                """);
    }
}
