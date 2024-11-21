package com.belhard.bookstore.controller;

import com.belhard.bookstore.controller.impl.ErrorCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet("/controller")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String commandParam = req.getParameter("command");
            Command command = AppListener.getContext().getBean(commandParam, Command.class);
            String page = command.process(req);
            req.getRequestDispatcher(page).forward(req, resp);
        } catch (Exception e) {
            log.error(e);
            Command command = AppListener.getContext().getBean(ErrorCommand.class);
            String page = command.process(req);
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}


