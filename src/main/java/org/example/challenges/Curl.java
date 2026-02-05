/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.challenges;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author bhats
 */
public class Curl {
    private int port = 80;
    private Boolean verbose = false;
    private Socket s;
    
    public Curl(boolean verbose) {
        this.verbose = verbose;
    }
    
    private String get_url_base(String url) {
        String[] url_parts = url.split("/");
        String website = url_parts[url_parts.length - 2];
        String[] website_parts = website.split(":");
        
        if (website_parts.length == 2) {
            this.port = Integer.parseInt(website_parts[1]);
            return website_parts[0];
        }
        return website;
    }
    
    private String build_request(String req_type, String base, String data, String content_type) {
        String req_string = req_type.toUpperCase() + " /" + req_type + " HTTP/1.1\r\n";
        req_string += "Host: " + base + "\r\n";
        
        if (req_type.equals("post") || req_type.equals("put")) {
            // req_string += "Content-Type: application/json\r\n";
            req_string += content_type + "\r\n";
            
            // test            
            JSONObject data_json = new JSONObject(data);
            // data_json.put("key", "value");
            String d = data_json.toString(2);
            req_string += "Content-Length: " + d.length() + "\r\n";
            
            req_string += "\n" + d + "\r\n";
        }
        
        req_string += "Connection: close\r\n";
        req_string += "\r\n";
        
        return req_string;
    }
    
    private JSONObject get_response(String base, String request, String request_type) throws UnknownHostException, IOException {
        s = new Socket(InetAddress.getByName(base), port);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.print(request);
        pw.flush();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String t;
        Boolean header_found = false;
        ArrayList<String> resp = new ArrayList<>();
        String header = "{\n";
        while((t = br.readLine()) != null) {
            if (t.contains("{")) {
                header_found = true;
                String h;
                while((h = br.readLine()) != null) {
                    if (h.contains("HTTP/1.1")) break;
                    header += h + "\n";
                }
            }
            if (header_found) break;
            resp.add(t);
        }
        
        if (verbose) {
            System.out.println("connecting to " + base + "\n");
            System.out.println("> Sending request " + request_type.toUpperCase() + " /" + request_type + " HTTP/1.1");
            System.out.println("> Host: " + base);
            System.out.println("> Accept: */*\n");
            for (String r : resp) {
                System.out.println("< " + r);
            }
            System.out.println(header);
        }
        br.close();
        return new JSONObject(header);
    }
    
    public JSONObject get(String url, String data, String content_type) throws UnknownHostException, IOException {
        String base = get_url_base(url);
        String request = build_request("get", base, data, content_type);
        return get_response(base, request, "get");
    }
    
    public JSONObject delete(String url, String data, String content_type) throws UnknownHostException, IOException {
        String base = get_url_base(url);
        String request = build_request("delete", base, data, content_type);
        return get_response(base, request, "delete");
    }
    
    public JSONObject post(String url, String data, String content_type) throws UnknownHostException, IOException {
        String base = get_url_base(url);
        String request = build_request("post", base, data, content_type);
        return get_response(base, request, "post");
    }
    
    public JSONObject put(String url, String data, String content_type) throws UnknownHostException, IOException {
        String base = get_url_base(url);
        String request = build_request("put", base, data, content_type);
        return get_response(base, request, "put");
    }
    
}
