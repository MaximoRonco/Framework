package com.saucedemo.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance;
    private Properties properties;

    private ConfigReader() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (Exception e) {
            throw new RuntimeException("No se pudo leer la configuración", e);
        }
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}