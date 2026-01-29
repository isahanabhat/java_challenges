package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import java.io.IOException;
import java.net.UnknownHostException;
import org.example.challenges.Curl;

// import org.example.challenges.TraceRoute;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        /*
        try {
            String host = "dns.google.com";
            TraceRoute t = new TraceRoute(host);
            t.trace_route();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        */
        String[] test_1 = {"cc_curl", "http://eu.httpbin.org/get"};
        String[] test_2 = {"cc_curl", "http://eu.httpbin.org/delete"};
        String[] test_3 = {"cc_curl", "-v", "http://eu.httpbin.org:80/delete"};
        
        // curl -X PUT http://eu.httpbin.org/put -d "{\"key\": \"value2\"}" -H "Content-Type: application/json"
        String[] test_4 = {"cc_curl", "-v", "http://eu.httpbin.org/post"};
        
        Curl c1 = new Curl(test_4);
        c1.parse_url();
        System.out.println();
        
        // Curl c2 = new Curl(test_2[1]);
        // c2.parse_url();
        // System.out.println();
        
    }
}