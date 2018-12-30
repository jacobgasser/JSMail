package com.jacobgasser.jsmail.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class PostGetParse {

    public String getPost(HttpExchange exchange) throws IOException {

        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    public String getGet(HttpExchange exchange) throws IOException {
        if(exchange.getRequestURI().getQuery() == null) {
            return "";
        }
        return exchange.getRequestURI().getQuery();
    }
    public HashMap<String, String> parseRequests(String request) {
        HashMap<String, String> map = new HashMap<String, String>();
        for(String fr : request.split("&")) {
            String[] mas = fr.split("=");
            mas[1] = mas[1].replaceAll("\\+", " ");
            map.put(mas[0],mas[1]);
        }
        return map;
    }

    public void disFile(File file, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, Files.readAllBytes(file.toPath()).length);
        OutputStream os = exchange.getResponseBody();
        os.write(Files.readAllBytes(file.toPath()));
        os.close();








    }
}
