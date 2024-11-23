package com.belhard.bookstore.controller.impl;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component("users")
public class UsersCommand implements Command {

    private final UserDao userDao;

    @Override
    public String process(HttpServletRequest req) {
        List<User> userList = userDao.getAll();
        req.setAttribute("users", userList);
        return "jsp/users.jsp";
    }
}

