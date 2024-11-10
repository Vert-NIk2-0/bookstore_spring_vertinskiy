package com.belhard.bookstore.controller;

import com.belhard.bookstore.connection.impl.ConnectionManagerImpl;
import com.belhard.bookstore.connection.impl.PropertiesUtilImpl;
import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.entity.Book;
import com.belhard.bookstore.dao.impl.BookDaoImpl;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.impl.BookDtoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/books")
public class BooksController extends HttpServlet {
    private final BookDao bookDao;

    public BooksController() {
        Logger logger = LogManager.getLogger(BooksController.class);
        PropertiesUtilImpl PROPERTIES_UTIL_IMPL = new PropertiesUtilImpl("/application.properties");
        String URL = PROPERTIES_UTIL_IMPL.get("db.url");
        String HOSTNAME = PROPERTIES_UTIL_IMPL.get("db.username");
        String PASSWORD = PROPERTIES_UTIL_IMPL.get("db.password");
        ConnectionManagerImpl connectionManager = new ConnectionManagerImpl(URL, HOSTNAME, PASSWORD, "org.postgresql.Driver");
        bookDao = new BookDaoImpl(connectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookList = bookDao.getAll();
        PrintWriter writer = resp.getWriter();


        writer.printf("""
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Books | bookstore</title>
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

        for(Book book : bookList) {
            writer.printf("""
                <div class="book-card">
                    <h1><a href="book?id=%s">%s</a></h1>
                    <p><span>Author:</span> %s</p>
                    <p><span>Year:</span> %s</p>
                    <p><span>Price:</span> %s$</p>
                </div>
            """,
                    book.getId(),
                    book.getBookname(),
                    book.getAuthor(),
                    book.getYear(),
                    book.getPrice());
        }

        writer.printf("""
            </div>
        </body>
        </html>
        """);



    }
}
