package v4.bddpom.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import static helper.WebHelper.startDriver;
import static helper.WebHelper.tearDown;

public class hooks {

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
