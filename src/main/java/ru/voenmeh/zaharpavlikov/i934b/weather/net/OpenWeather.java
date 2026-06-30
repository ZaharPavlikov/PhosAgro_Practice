package ru.voenmeh.zaharpavlikov.i934b.weather.net;

import ru.voenmeh.zaharpavlikov.i934b.weather.Config;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenWeather {
    public static String load(String lat, String lon) throws Exception {
        // Теперь мы берем базу URL из конфига, а координаты подставляем динамически
        String baseUrl = Config.get("api.base.url");
        String urlString = String.format(
                "%s?latitude=%s&longitude=%s&start_date=2026-06-01&end_date=2026-06-01&hourly=temperature_2m,rain,cloud_cover",
                baseUrl, lat, lon
        );

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String input;
            while ((input = in.readLine()) != null) response.append(input);
            return response.toString();
        }
    }

    public static void parse(String json, String city) {
        String[] times = parseStringArray(json, "time");
        double[] temps = parseDoubleArray(json, "temperature_2m");
        double[] rain = parseDoubleArray(json, "rain");
        double[] clouds = parseDoubleArray(json, "cloud_cover");

        System.out.println("\n=== Погода в " + city + " ===");
        for (int i = 0; i < times.length; i++) {
            System.out.printf("Дата/Время: %s | Темп: %.1f°C | Дождь: %.1f мм | Облака: %.0f%%%n",
                    times[i], temps[i], rain[i], clouds[i]);
        }
    }

    private static String[] parseStringArray(String json, String key) {
        String search = "\"" + key + "\":[";
        int start = json.indexOf(search) + search.length();
        int end = json.indexOf("]", start);
        return json.substring(start, end).replace("\"", "").split(",");
    }

    private static double[] parseDoubleArray(String json, String key) {
        String[] parts = parseStringArray(json, key);
        double[] values = new double[parts.length];
        for (int i = 0; i < parts.length; i++) values[i] = Double.parseDouble(parts[i]);
        return values;
    }
}
