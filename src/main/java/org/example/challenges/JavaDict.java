package org.example.challenges;

import org.json.JSONArray;
import org.json.JSONObject;

/*
Build a Java class that "mimics" the python dict data structure. Instead of [], use put/get methods.
The final version of the dict must support multiple levels (nested) + different object types in the same dict. (similar to python dict).
Weightage will be given to thought process about how to design the class.
I want you to use your Java OCP knowledge and think of a sophisticated way to design so that your class is versatile.
Start by building a simple prototype - of only 1 level and simple key/value pairs (i.e. no 2nd level / nested dicts/lists).
Build iteratively and more and more complexity - 1 step at a time - as you'll likely do in a complex project setting.
Use any JSON package library internally to speed up your work.
I found com.google.gson a good one, but many people use jackson also.
You'll need to select the one that supports generic objects (instead of just basic data types and strings).
 */

public class JavaDict {
    public JSONArray jArray;
    public JSONObject jObject;
    public JavaDict() {
        this.jArray = new JSONArray();
        this.jObject = new JSONObject();
    }
    
    public void json_test() {
        JSONObject jo = new JSONObject();
        jo.put("name", "jon doe");
        jo.put("age", "22");
        jo.put("city", 2);
        System.out.println(jo.toString());
        jArray.put(Boolean.TRUE);
        jArray.put("lorem ipsum");
        jArray.put(jo);
        System.out.println(jArray.toString(4));
    }
    
    public void put(Object key, Object value) {
        jObject.put(String.valueOf(key), value);
    }
    public String jsonToString(int indent) {
        return jObject.toString(indent);
    }
    public Object get(Object key) {
        String key_str = String.valueOf(key);
        return jObject.get(key_str);
    }
}
