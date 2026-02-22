package v2.pom.test;

import helper.utils;
import v2.pom.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class LoginTest {


        protected WebDriver driver;
        protected LoginPage loginPage;

        @BeforeMethod
        public void setUp() {
            driver = utils.getDriver("chrome");
            driver.get("https://www.demoblaze.com/");
            // POM: Initialize page object with driver
            loginPage = new LoginPage(driver);
        }

        @AfterMethod
        public void tearDown() {
            utils.quitDriver();
        }
    }
}
