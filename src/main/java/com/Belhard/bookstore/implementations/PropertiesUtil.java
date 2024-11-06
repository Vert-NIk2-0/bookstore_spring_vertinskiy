package com.Belhard.bookstore.implementations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static final String PROPERTIES_FILE = "application.properties";
    private static final Properties PROPERTIES = new Properties();

    private PropertiesUtil() {

    }

    static {
        loadProperties();
    }

    public static String get (String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {

        try (InputStream input = PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

