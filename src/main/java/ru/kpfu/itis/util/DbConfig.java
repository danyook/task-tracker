package ru.kpfu.itis.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfig {
    private static Properties properties = new Properties();

    public DbConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find database.properties");
                return;
            }

            // Загружаем свойства из файла
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getDatabaseDriver() {
        return properties.getProperty("driver");
    }

    public static String getDatabaseUrl() {
        return properties.getProperty("url");
    }

    public static String getDatabaseUsername() {
        return properties.getProperty("username");
    }

    public static String getDatabasePassword() {
        return properties.getProperty("password");
    }
}
