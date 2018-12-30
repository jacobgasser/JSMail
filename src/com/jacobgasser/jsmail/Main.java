package com.jacobgasser.jsmail;

import com.jacobgasser.jsmail.utils.PostGetParse;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;

public class Main {

    public static void main(String[] arguments) throws IOException {

        System.out.println("JSMail is starting...");
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(80), 0);
        HttpContext cAll = httpServer.createContext("/", new Main()::handle);
        httpServer.start();
        System.out.println("JSMail is online! (yaaay)");

    }

    public void handle(HttpExchange exchange) throws IOException {

        PostGetParse pgp = new PostGetParse();

        if(pgp.getGet(exchange).contains("oml")) {
            exchange.sendResponseHeaders(200, "Ok Then".getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write("Ok Then".getBytes());
            os.close();
        }


    }
}
