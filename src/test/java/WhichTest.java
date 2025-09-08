import org.example.challenges.Which;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class WhichTest {
    @Test
    public void test_01() {
        String[] test1 = {"java"};
        Which which_cmd = new Which();
        ArrayList<String> res = new ArrayList<>();
        // res.add("C:\\Users\\bhats\\AppData\\Local\\Microsoft\\WindowsApps");
        res.add("C:\\Program Files\\Common Files\\Oracle\\Java\\javapath");
        System.out.println(which_cmd.find_location(test1).toString());
        Assert.assertEquals(res, which_cmd.find_location(test1));
    }

    @Test
    public void test_02() {
        String[] test1 = {"java","go"};
        Which which_cmd = new Which();
        ArrayList<String> res = new ArrayList<>();
        res.add("C:\\Program Files\\Common Files\\Oracle\\Java\\javapath");
        Assert.assertEquals(res, which_cmd.find_location(test1));
    }

}
