package com.jacobgasser.jsmail.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.nio.file.Files;

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
    public String parseRequests(String request) {
        return request.replaceAll("\\+", " ");
    }

    public void disFile(File file, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, Files.readAllBytes(file.toPath()).length);
        OutputStream os = exchange.getResponseBody();
        os.write(Files.readAllBytes(file.toPath()));
        os.close();
    }
}
