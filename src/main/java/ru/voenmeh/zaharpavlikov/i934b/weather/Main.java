package ru.voenmeh.zaharpavlikov.i934b.weather;

import ru.voenmeh.zaharpavlikov.i934b.weather.net.OpenWeather;
import ru.voenmeh.zaharpavlikov.i934b.weather.Config;

public class Main {
    public static void main(String[] args) {
        // Мы просто перечисляем ID городов, которые есть в конфиге
        String[] cityKeys = {"spb", "cherepovets"};

        for (String key : cityKeys) {
            String name = Config.get(key + ".name");
            String lat = Config.get(key + ".lat");
            String lon = Config.get(key + ".lon");

            try {
                System.out.println("--- Запрос данных для: " + name + " ---");
                String json = OpenWeather.load(lat, lon);
                OpenWeather.parse(json, name);
            } catch (Exception e) {
                System.err.println("Ошибка при загрузке данных для " + key + ": " + e.getMessage());
            }
        }
    }
}