import org.testng.annotations.Test;
import page_objects.GoogleHomePage;

public class TestClass extends TestRunner {
    //This test method checks project run
    @Test
    public void testMethod() {
        new GoogleHomePage().openHomePage().searchFor("dog");
    }
}
