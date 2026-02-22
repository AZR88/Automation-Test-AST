package v1.Linear;

import helper.utils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class LoginTest {

        protected WebDriver driver;

        @BeforeMethod
        public void setUp() {
            // Linear: Direct call, no abstraction
            driver = utils.getDriver("chrome");
            driver.get("https://www.demoblaze.com/");
        }

        @AfterMethod
        public void tearDown() {
            utils.quitDriver();
        }
    }
}
