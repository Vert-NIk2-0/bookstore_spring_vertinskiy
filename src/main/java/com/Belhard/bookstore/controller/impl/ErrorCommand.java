package com.belhard.bookstore.controller.impl;

import com.belhard.bookstore.controller.Command;
import jakarta.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {


    public String process(HttpServletRequest req){
        return "jsp/error.jsp";
    }
}


