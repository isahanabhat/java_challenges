
import java.util.Arrays;
import org.example.challenges.JavaDict;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bhats
 */
public class JavaDictTest {
    public JavaDict j;
    
    public JavaDictTest() {
        j = new JavaDict();
        
        j.put("working", false);
        j.put("name", "sahana");
        j.put("age", 22);
        j.put(2004, 2605);
        String[] arr = {"hello","world"};
        j.put("array", arr);
        
    }
    @Test
    public void test_01() {
        System.out.println("TEST 1");
        Assert.assertEquals(false, j.get("working"));
        System.out.println();
    }
    
    @Test
    public void test_02() {
        System.out.println("TEST 2");
        Assert.assertEquals(22, j.get("age"));
        System.out.println();
    }
    
    @Test
    public void test_03() {
        System.out.println("TEST 3");
        String[] arr = {"hello","world"};
        JSONArray l = new JSONArray(Arrays.asList(arr));
        Assert.assertEquals(l.toString(), j.get("array").toString());
        System.out.println();
    }
    
    @Test
    public void test_04() {
        System.out.println("TEST 4");
        JavaDict val = new JavaDict();
        val.put("Maths", 95);
        val.put("Chemistry", 95);
        val.put("CSE", 93);
        j.put("12th marks", val);
        
        // Object res = j.get("12th marks");
        JavaDict marks = new JavaDict(j.get("12th marks"));
        
        String r = "{\"Maths\":95,\"Chemistry\":95,\"CSE\":93}";
        Assert.assertEquals(r, j.get("12th marks").toString());
        System.out.println();
    }
    @Test
    public void test_05() {
        System.out.println("TEST 5");
        JavaDict val = new JavaDict();
        val.put("Maths", 95);
        val.put("Chemistry", 95);
        val.put("CSE", 93);
        j.put("12th marks", val);
        
        JavaDict marks = new JavaDict(j.get("12th marks"));
        Assert.assertEquals(95, marks.get("Maths"));
        System.out.println();
    }
}
