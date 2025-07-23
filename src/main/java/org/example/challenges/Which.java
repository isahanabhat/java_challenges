package org.example.challenges;

import java.io.File;
import java.util.*;

public class Which {
    private final String[] env_var;

    public Which() {
        String path_var = System.getenv("Path");
        this.env_var = path_var.split(File.pathSeparator);
    }

    public ArrayList<String> find_location(String[] command_list) {
        ArrayList<String> found_path = new ArrayList<>();
        for (String cmd : command_list) {
            String temp = cmd + ".exe";
            for (String path : env_var) {
                boolean check = new File(path, temp).exists();
                if (check) {
                    found_path.add(path);
                    break;
                }
            }
        }
        return found_path;
    }

    public void display_env_paths() {
        System.out.println(Arrays.toString(env_var));
    }
}
