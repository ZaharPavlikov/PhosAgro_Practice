package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws Exception {

        System.setOut(new PrintStream(System.out, true, "UTF-8"));

        String url = "https://world-weather.ru/";

        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .get();

        System.out.println(document.title());
    }
}