/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.challenges;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Map;

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
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        pw.print("GET /" + req +  " HTTP/1.1\r\n");
        pw.print("Host: "+ website +"\r\n");
        pw.print("Connection: close\r\n\r\n");
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
