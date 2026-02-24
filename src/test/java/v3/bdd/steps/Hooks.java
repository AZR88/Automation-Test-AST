package v3.bdd.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import static helper.WebHelper.startDriver;
import static helper.WebHelper.tearDown;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void beforeTest() {
        String browser = System.getProperty("browser", "chrome"); // Default to chrome if no property is set
        System.out.println("Running tests on: " + browser);
        startDriver(browser);
    }

    @After
    public void afterTest() {
        tearDown();
    }
}
