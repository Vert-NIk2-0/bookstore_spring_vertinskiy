package com.belhard.bookstore.controller;

import com.belhard.bookstore.connection.impl.ConnectionManagerImpl;
import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.entity.User;
import com.belhard.bookstore.dao.impl.BookDaoImpl;
import com.belhard.bookstore.dao.entity.Book;
import com.belhard.bookstore.connection.impl.PropertiesUtilImpl;
import com.belhard.bookstore.dao.impl.UserDaoImpl;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.impl.BookDtoImpl;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@WebServlet("/book")
public class BookController extends HttpServlet {
    private final BookDao bookDao;

    public BookController() {
        Logger logger = LogManager.getLogger(BookController.class);
        PropertiesUtilImpl PROPERTIES_UTIL_IMPL = new PropertiesUtilImpl("/application.properties");
        String URL = PROPERTIES_UTIL_IMPL.get("db.url");
        String HOSTNAME = PROPERTIES_UTIL_IMPL.get("db.username");
        String PASSWORD = PROPERTIES_UTIL_IMPL.get("db.password");
        ConnectionManagerImpl connectionManager = new ConnectionManagerImpl(URL, HOSTNAME, PASSWORD, "org.postgresql.Driver");
        bookDao = new BookDaoImpl(connectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqId = req.getParameter("id");
        long id;
        Book book;
        try {
            id = Long.parseLong(reqId);
            book = bookDao.getById(id);
        } catch (RuntimeException e) {
            showErrorPage(resp);
            return;
        }



        PrintWriter writer = resp.getWriter();
        if (book != null) {
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
                            <p><span>ID:</span> %s</p>
                            <p><span>Author:</span> %s</p>
                            <p><span>ISBN:</span> %s</p>
                            <p><span>Number Of Pages:</span> %s</p>
                            <p><span>Year:</span> %s</p>
                            <p><span>Price:</span> %s$</p>
                            <p><span>Time:</span> %s</p>
                        </div>
                    </body>
                    </html>
                    """,
                    book.getBookname(),
                    book.getBookname(),
                    book.getId(),
                    book.getAuthor(),
                    book.getIsbn(),
                    book.getNumberOfPages(),
                    book.getYear(),
                    book.getPrice(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm:ss")));
        } else {
            showErrorPage(resp);
        }
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


