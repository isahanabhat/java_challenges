package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import java.io.IOException;
import java.net.UnknownHostException;
import org.example.challenges.TraceRoute;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Hello World!");
        try {
            String host = "dns.google.com";
            TraceRoute t = new TraceRoute(host);
            t.trace_route();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        
    }
}