package v2.pom.test;

import helper.utils;
import v2.pom.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    protected WebDriver driver;
    protected LoginPage login;

    @BeforeMethod
    public void setUp() {
        driver = utils.getDriver("chrome");
        driver.get("https://www.demoblaze.com/");
        // Inisialisasi Page Object
        login = new LoginPage(driver);
    }

    @Test
    public void testLoginWithPOM() {
        login.clickLoginMenu();
        login.enterCredentials("azriel_test", "password123");
        login.clickLogin();

        System.out.println("Skenario POM Login Selesai.");
    }

    @AfterMethod
    public void tearDown() {
        utils.quitDriver();
    }
}