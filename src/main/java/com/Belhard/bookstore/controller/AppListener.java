package com.belhard.bookstore.controller;

import com.belhard.bookstore.AppConfig;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebListener
@Log4j2
public class AppListener implements ServletContextListener {

    @Getter
    private static AnnotationConfigApplicationContext context;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("The context is being initialized...");
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        log.info("The context has been initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        context.close();
        log.info("Context destroyed");
    }
}
