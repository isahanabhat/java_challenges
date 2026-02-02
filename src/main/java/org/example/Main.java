package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import java.io.IOException;
import java.net.UnknownHostException;
import org.example.challenges.Curl;

// import org.example.challenges.TraceRoute;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        // curl -X PUT http://eu.httpbin.org/put -d "{\"key\": \"value2\"}" -H "Content-Type: application/json"
        
        Curl c1 = new Curl();
        c1.get("http://eu.httpbin.org/get");
        System.out.println("-----------------------------------------\n");
        c1.delete("http://eu.httpbin.org/delete");
        System.out.println("-----------------------------------------\n");
        c1.post("http://eu.httpbin.org/post");
        
        /*
        String d = "{\"key\": \"value2\"}";
        JSONObject data_json = new JSONObject(d);
        System.out.println(data_json.toString(4));
        */
    }
}