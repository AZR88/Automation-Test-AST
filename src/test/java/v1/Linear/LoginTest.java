package v1.Linear;

import helper.utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// PASTIKAN HANYA MENGGUNAKAN IMPORT TESTNG
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = utils.getDriver("chrome");
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void testLoginStep() {
        // Implementasi linear (tradisional) tanpa abstraksi
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("azriel_test");
        driver.findElement(By.id("loginpassword")).sendKeys("password123");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
    }

    @AfterMethod
    public void tearDown() {
        utils.quitDriver();
    }
}