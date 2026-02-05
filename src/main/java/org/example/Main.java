package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import java.io.IOException;
import java.net.UnknownHostException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.example.challenges.Curl;
import org.json.JSONObject;

// import org.example.challenges.TraceRoute;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException, ParseException {
        // curl -X PUT http://eu.httpbin.org/put -d "{\"key\": \"value2\"}" -H "Content-Type: application/json"
        
        Options options = new Options();
        options.addOption("cmd", true, "Command type");
        options.addOption("X", true, "HTTP Request type");
        options.addOption("D", true, "Data");
        options.addOption("H", true, "Content-Type of data");
        options.addOption("U", true, "URL"); // for now
        options.addOption("V", false, "Verbose");
        
        CommandLineParser parser = new DefaultParser();
        
        String req_type = "", data = "", content_type = "", url = "", type = "";
        boolean verbose;

        CommandLine cmd_line = parser.parse(options, args);
        type = cmd_line.getOptionValue("cmd");
        
        if (type.equals("curl")) {
            req_type = cmd_line.hasOption("X") ? cmd_line.getOptionValue("X") : "GET";
            data = cmd_line.hasOption("D") ? cmd_line.getOptionValue("D") : "";
            content_type = cmd_line.hasOption("H") ? cmd_line.getOptionValue("H") : "Content-type: application/x-www-form-urlencoded";
            url = cmd_line.getOptionValue("U"); // for now
            verbose =  cmd_line.hasOption("V");
            
            JSONObject response = new JSONObject();
            
            Curl c1 = new Curl(verbose);
            if (req_type.equals("GET")) { response = c1.get(url, data, content_type); }
            else if (req_type.equals("DELETE")) { response = c1.delete(url, data, content_type); } 
            else if (req_type.equals("POST")) { response = c1.post(url, data, content_type); } 
            else if (req_type.equals("PUT")) { response = c1.put(url, data, content_type); }
        }
    }
}