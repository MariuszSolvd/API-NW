package com.solvd.utilis;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            FileInputStream file = new FileInputStream("src/main/recources/config.properties");
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Can load the config file from resources");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
