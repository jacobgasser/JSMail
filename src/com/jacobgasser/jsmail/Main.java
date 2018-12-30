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
        for(String key : post.keySet()) {
            String val = post.get(key);

            switch (key.toLowerCase()) {

                case "say" :
                    pgp.returnThis(exchange, val);
                    continue;


            }

        }
        

            pgp.returnThis(exchange, "null");
        }


    }

