package org.example;

import org.example.challenges.Which;

import java.io.File;
import java.util.*;
import java.util.Arrays;
import java.util.Objects;
import org.example.challenges.JavaDict;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String[] args_temp = {"java", "go", "python"};
        Which which_cmd = new Which();
        ArrayList<String> result = which_cmd.find_location(args_temp);
        for (String r : result) {
            System.out.println(r);
        }
        
        // j.json_test();
        // System.out.println(j.jsonToString(4));
        
        JavaDict j = new JavaDict();
        j.put("sahana", 21);
        j.put("working", false);
        j.put(2004, 2025);
        /*
        System.out.println(j.jsonToString(4));
        System.out.println("sahana : " + j.get("sahana"));
        System.out.println("working : " + j.get("working"));
        System.out.println("2004 : " + j.get(2004));
        */
    }
}