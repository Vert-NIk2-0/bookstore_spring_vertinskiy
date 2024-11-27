package com.belhard.bookstore;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;

@Configuration
@ComponentScan
@PropertySource("classpath:/application.properties")
public class AppConfig {

    @Value("${db.driver}")
    private String driver;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.poolSize}")
    private int poolSize;

    @Bean
    public JdbcTemplate template (DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedTemplate (DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource source = new HikariDataSource();
        source.setDriverClassName(driver);
        source.setJdbcUrl(url);
        source.setUsername(username);
        source.setPassword(password);
        source.setMaximumPoolSize(poolSize);
        return source;
    }
}
