package com.belhard.bookstore.controller.impl;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.entity.Book;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component("book")
public class BookCommand implements Command {

    private final BookDao bookDao;

    @Override
    public String process(HttpServletRequest req){
        long id = getId(req);
        Book book = bookDao.getById(id);
        req.setAttribute("book", book);
        req.setAttribute("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss")));
        return "jsp/book.jsp";

    }

    private long getId(HttpServletRequest req) {
        String reqId = req.getParameter("id");
        return Long.parseLong(reqId);
    }
}


