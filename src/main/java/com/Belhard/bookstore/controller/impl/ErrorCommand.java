package com.belhard.bookstore.controller.impl;

import com.belhard.bookstore.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component("error")
@NoArgsConstructor
public class ErrorCommand implements Command {

    public String process(HttpServletRequest req){
        return "jsp/error.jsp";
    }
}


