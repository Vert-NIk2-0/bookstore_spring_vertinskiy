package com.belhard.bookstore.controller.impl;

import com.belhard.bookstore.controller.Command;
import com.belhard.bookstore.dao.UserDao;
import com.belhard.bookstore.dao.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component("user")
public class UserCommand implements Command {

    private final UserDao userDao;

    @Override
    public String process(HttpServletRequest req){
        long id = getId(req);
        User user = userDao.getById(id);
        req.setAttribute("user", user);
        return "jsp/user.jsp";
    }


    private long getId(HttpServletRequest req) {
        String reqId = req.getParameter("id");
        long id = Long.parseLong(reqId);
        return id;
    }
}
