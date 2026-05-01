package v1.Linear;

import helper.WebHelper;
import helper.WaitElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static helper.WebHelper.driver;

public class LoginTest_I2 {

    @BeforeMethod
    public void setup() {
        WebHelper.startDriver("chrome");
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void testLoginValid() {
        WaitElement.waitForElement(By.id("login2"));
        driver.findElement(By.id("login2")).click();

        WaitElement.waitForElement(By.cssSelector("#loginusername"));
        WebElement userField = driver.findElement(By.cssSelector("#loginusername"));
        userField.clear();
        userField.sendKeys("Beta123");

        WaitElement.waitForElement(By.xpath("//*[@id='loginpassword']"));
        WebElement passField = driver.findElement(By.xpath("//*[@id='loginpassword']"));
        passField.clear();
        passField.sendKeys("123");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        WaitElement.waitForElement(By.id("nameofuser"));
        String actualUser = driver.findElement(By.id("nameofuser")).getText();
        Assert.assertTrue(actualUser.contains("Beta123"), "Valid login gagal");
    }

    @Test
    public void testLoginInvalidUsername() {
        WaitElement.waitForElement(By.id("login2"));
        driver.findElement(By.id("login2")).click();

        WaitElement.waitForElement(By.cssSelector("#loginusername"));
        WebElement userField = driver.findElement(By.cssSelector("#loginusername"));
        userField.clear();
        userField.sendKeys("adawqrsd");

        WaitElement.waitForElement(By.xpath("//*[@id='loginpassword']"));
        WebElement passField = driver.findElement(By.xpath("//*[@id='loginpassword']"));
        passField.clear();
        passField.sendKeys("222");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Wrong credentials.");
        alert.accept();
    }

    @Test
    public void testLoginInvalidPassword() {
        WaitElement.waitForElement(By.id("login2"));
        driver.findElement(By.id("login2")).click();

        WaitElement.waitForElement(By.cssSelector("#loginusername"));
        WebElement userField = driver.findElement(By.cssSelector("#loginusername"));
        userField.clear();
        userField.sendKeys("Beta123");

        WaitElement.waitForElement(By.xpath("//*[@id='loginpassword']"));
        WebElement passField = driver.findElement(By.xpath("//*[@id='loginpassword']"));
        passField.clear();
        passField.sendKeys("222");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Wrong password.");
        alert.accept();
    }

    @Test
    public void testLoginEmptyUsernamePassword() {
        WaitElement.waitForElement(By.id("login2"));
        driver.findElement(By.id("login2")).click();

        WaitElement.waitForElement(By.cssSelector("#loginusername"));
        WebElement userField = driver.findElement(By.cssSelector("#loginusername"));
        userField.clear();
        userField.sendKeys("");

        WaitElement.waitForElement(By.xpath("//*[@id='loginpassword']"));
        WebElement passField = driver.findElement(By.xpath("//*[@id='loginpassword']"));
        passField.clear();
        passField.sendKeys("");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Please fill out Username and Password.");
        alert.accept();
    }

    @AfterMethod
    public void tearDown() {
        WebHelper.tearDown();
    }
}