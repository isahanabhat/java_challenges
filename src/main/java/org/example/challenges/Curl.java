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
    // cccurl http://eu.httpbin.org/get
    public String[] test = {"cccurl", "http://eu.httpbin.org/get"};
    
    public String website;
    public String req;
    public String protocol;
    public int port = 80;
    public Boolean verbose = false;
    
    public Curl(String[] args) {
        // temporary: assume verbose (-v) only comes in index 1
        // next : implement org.apache.commons.cli.* for cmd parser
        
        int url_index = 1;
        if (args[1].equals("-v")) {
            this.verbose = true;
            url_index = 2;
        }
        String[] url_parts = args[url_index].split("/");
        
        int n = url_parts.length;
        this.website = url_parts[n-2];
        this.req = url_parts[n-1];
        this.protocol = url_parts[0];
        
        String[] website_parts = website.split(":");
        
        if (website_parts.length == 2) {
            this.port = Integer.parseInt(website_parts[1]);
            this.website = website_parts[0];
        }
    }
    
    public void parse_url() throws UnknownHostException, IOException {
        Socket s = new Socket(InetAddress.getByName(website), port);
        
        String req_string = req.toUpperCase() + " /" + req + " HTTP/1.1\r\n";
        req_string += "Host: " + website + "\r\n";
        
        if (req.equals("post") || req.equals("put")) {
            req_string += "Content-Type: application/json\r\n";
            // test
            String data;
            
            JSONObject data_json = new JSONObject();
            data_json.put("key", "value");
            data = data_json.toString(2);
            req_string += "Content-Length: " + data.length() + "\r\n";
            
            req_string += "\n" + data + "\r\n";
        }
        
        req_string += "Connection: close\r\n";
        req_string += "\r\n"; // Empty line indicates end of request header
        
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.print(req_string);
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
                while((h = br.readLine()) != null)
                    header += h + "\n";
            }
            if (header_found) break;
            resp.add(t);
        }
        
        if (verbose) {
            System.out.println("connecting to " + website + "\n");
            System.out.println("> Sending request " + req.toUpperCase() + " /" + req + " HTTP/1.1");
            System.out.println("> Host: " + website);
            System.out.println("> Accept: */*\n");
            for (String r : resp) {
                System.out.println("< " + r);
            }
        }
        
        System.out.println(header);
        br.close();
        
        s.close();
    }
}
