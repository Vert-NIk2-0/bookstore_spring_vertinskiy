package com.belhard.bookstore.connection.impl;

import com.belhard.bookstore.connection.PropertiesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtilImpl implements PropertiesUtil {
    private final Properties PROPERTIES;

    public PropertiesUtilImpl(String filepath) {
        try (InputStream input = PropertiesUtilImpl.class.getClassLoader().getResourceAsStream(filepath)) {
            PROPERTIES = new Properties();
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get (String key) {
        return PROPERTIES.getProperty(key);
    }
}

