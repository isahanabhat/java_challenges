package org.example;

import org.example.challenges.Which;

import java.io.File;
import java.util.*;
import java.util.Arrays;
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String[] args_temp = {"java", "go"};
        Which which_cmd = new Which();
        ArrayList<String> result = which_cmd.find_location(args_temp);
        for (String r : result) {
            System.out.println(r);
        }
    }
}