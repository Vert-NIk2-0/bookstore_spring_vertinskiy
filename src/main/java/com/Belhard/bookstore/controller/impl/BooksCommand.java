package com.belhard.bookstore.controller.impl;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.entity.Book;
import com.belhard.bookstore.dao.impl.BookDaoImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component("books")
public class BooksCommand implements Command {

    private final BookDao bookDao;

    @Override
    public String process(HttpServletRequest req) {
        List<Book> bookList = bookDao.getAll();
        req.setAttribute("books", bookList);
        return "jsp/books.jsp";
    }
}
