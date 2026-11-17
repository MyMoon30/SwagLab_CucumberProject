package com.swaglabs.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file.", e);
        }
    }

    public static String getProperty(String key) {
        String sysProp = System.getProperty(key);
        return (sysProp != null && !sysProp.isEmpty()) ? sysProp : properties.getProperty(key);
    }
}
