package org.example.challenges;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
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
    
    public JavaDict(Object dict) {
        // System.out.println("Inside javaDict class, dict = " + dict.toString() + " " + dict.getClass());
        this.jObject = (JSONObject) dict;
    }
    
    public void json_test() {
        JSONObject jo = new JSONObject();
        jo.put("name", "jon doe");
        jo.put("age", "22");
        jo.put("city", 2);
        jArray.put(Boolean.TRUE);
        jArray.put("lorem ipsum");
        jArray.put(jo);
    }
    
    public void put(Object key, Object value) {
        jObject.put(String.valueOf(key), value);
    }
    
    public void put(Object key, Object[] value) {
        List<Object> l = Arrays.asList(value);
        jObject.put(String.valueOf(key), new JSONArray(l));
    }
    
    public void put(Object key, JavaDict value) {
        String v = value.jsonToString(0);
        jObject.put(String.valueOf(key), v);
    }
    
    public String jsonToString(int indent) {
        return jObject.toString(indent);
    }
    
    public Object get(Object key) {
        String key_str = String.valueOf(key);
        Object val = jObject.get(key_str);
        JSONObject ret_val;
        try {
            ret_val = new JSONObject(val); // this is converting ret_val to bytes since it has \
            JSONArray byteArray = ret_val.getJSONArray("bytes"); 
            
            byte[] bytes = new byte[byteArray.length()];
            for (int i = 0; i < byteArray.length(); i++) {
                bytes[i] = (byte) byteArray.getInt(i);
            }
            
            String jStr = new String(bytes, StandardCharsets.UTF_8);
            ret_val = new JSONObject(jStr);
            
        } catch (Exception e) {
            // System.out.println("EXCEPTION "+ e.getMessage());
            return val;
        }
        return ret_val;
    }
    
    
}
