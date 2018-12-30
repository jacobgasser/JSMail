package com.jacobgasser.jsmail;

import com.jacobgasser.jsmail.utils.PostGetParse;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.HashMap;

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

           HashMap<String, String> post =  pgp.parseRequests(pgp.getPost(exchange));
           String thing1 = post.get("c");
            exchange.sendResponseHeaders(200, thing1.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(thing1.getBytes());
            os.close();
        }


    }

