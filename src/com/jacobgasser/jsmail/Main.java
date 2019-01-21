package com.jacobgasser.jsmail;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jacobgasser.jsmail.utils.PostGetParse;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

        List<InetAddress> addresses = new ArrayList<>();


    public static void main(String[] arguments) throws IOException {

        System.out.println("JSMail is starting...");
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(81), 0);
        HttpContext cAll = httpServer.createContext("/", new Main()::handle);
        httpServer.start();
        System.out.println("JSMail is online! (yaaay)");

    }

    public void handle(HttpExchange exchange) throws IOException {
        addresses.add(exchange.getRemoteAddress().getAddress());

        System.out.println(exchange.getLocalAddress());
        PostGetParse pgp = new PostGetParse();
        HashMap<String, String> post = pgp.parseRequests(pgp.getPost(exchange));
        for (String key : post.keySet()) {
            String val = post.get(key);

            System.out.println(val);
            switch (key.toLowerCase()) {

                case "say":
                    pgp.returnThis(exchange, val);

                    continue;
                case "send":

                    if (!post.containsKey("message")) return;
                    if (!post.containsKey("sendto")) return;
                    if (!post.containsKey("subject")) return;

                    String message = post.get("message");
                    String subject = post.get("subject");
                    String sendTo = post.get("sendto");
                    new Email().email(message, subject, sendTo);
                    pgp.returnThis(exchange, "Attempted to send Email");


            }

        }


        pgp.returnThis(exchange, "null");
    }


}

