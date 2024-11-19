package com.belhard.bookstore.controller;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebListener
@Log4j2
public class AppListener implements ServletContextListener {

    @Getter
    private static ClassPathXmlApplicationContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        context = new ClassPathXmlApplicationContext("config.xml");
//        CommandFactory instance = CommandFactory.INSTANCE;
        log.info("Context initialized" );
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        context.close();
//        CommandFactory.INSTANCE.close();
        log.info("Context destroyed");
    }
}
