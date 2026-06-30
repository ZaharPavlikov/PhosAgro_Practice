package ru.voenmeh.zaharpavlikov.i934b.weather;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        // Загружаем файл из папки resources при запуске программы
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(is);
        } catch (Exception e) {
            System.err.println("Ошибка чтения конфига: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}