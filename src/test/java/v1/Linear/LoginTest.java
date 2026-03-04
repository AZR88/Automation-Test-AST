package v1.Linear;

import helper.WebHelper;
import helper.WaitElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static helper.WebHelper.driver;

public class LoginTest {

    @BeforeMethod
    public void setup() {
        WebHelper.startDriver("chrome");
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void testLoginValid() {
        // Step 1
        WaitElement.waitForElement(By.id("login2"));
        driver.findElement(By.id("login2")).click();

        // Step 2
        WaitElement.waitForElement(By.cssSelector("#loginusername"));
        WebElement userField = driver.findElement(By.cssSelector("#loginusername"));
        userField.clear();
        userField.sendKeys("Beta123");

        // Step 3
        WaitElement.waitForElement(By.xpath("//*[@id='loginpassword']"));
        WebElement passField = driver.findElement(By.xpath("//*[@id='loginpassword']"));
        passField.clear();
        passField.sendKeys("123");

        // Step 4
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        // Step 5
        WaitElement.waitForElement(By.xpath("//a[@id='nameofuser']"));
        String actualUser = driver.findElement(By.xpath("//a[@id='nameofuser']")).getText();
        Assert.assertTrue(actualUser.contains("Beta123"));
    }

    @Test


    @AfterMethod
    public void tearDown() {
        WebHelper.tearDown();
    }
}