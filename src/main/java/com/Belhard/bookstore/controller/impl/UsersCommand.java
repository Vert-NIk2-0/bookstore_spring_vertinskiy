package com.belhard.bookstore.controller.impl;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UsersCommand implements Command {
    private final UserDao userDao;

    @Override
    public String process(HttpServletRequest req) {
        List<User> userList = userDao.getAll();
        req.setAttribute("users", userList);
        return "jsp/users.jsp";
    }
}
