import org.example.challenges.Which;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class WhichTest {
    @Test
    public void test_01() {
        String[] test1 = {"python","java"};
        Which which_cmd = new Which();
        ArrayList<String> res = new ArrayList<>();
        res.add("C:\\Users\\bhats\\SAHANABHAT\\pyenv\\venv1\\Scripts");
        res.add("C:\\Program Files\\Common Files\\Oracle\\Java\\javapath");
        Assert.assertEquals(res, which_cmd.find_location(test1));
    }

    @Test
    public void test_02() {
        String[] test1 = {"python","go"};
        Which which_cmd = new Which();
        ArrayList<String> res = new ArrayList<>();
        res.add("C:\\Users\\bhats\\SAHANABHAT\\pyenv\\venv1\\Scripts");
        Assert.assertEquals(res, which_cmd.find_location(test1));
    }

}
