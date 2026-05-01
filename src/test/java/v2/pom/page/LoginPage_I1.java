package v2.pom.page;

import helper.WaitElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage_I1 extends BasePage {

    // Locators
    public static By loginButton = By.id("login2");
    public static By usernameInputText = By.cssSelector("#user_v2");
    public static By passwordInputText = By.xpath("//*[@id='loginpassword']");
    public static By submitButton = By.xpath("//button[text()='Log in']");
    public static By userID = By.xpath("//a[@id='nameofuser']");

    // Check URL
    public static boolean isUrlCorrect(WebDriver driver, String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Click Login Button
    public static boolean clickLoginButton(WebDriver driver) {
        try {
            click(driver, loginButton);
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    // Input Username
    public static String inputUsername(WebDriver driver, String username) {
        sendKeys(driver, usernameInputText, username);
        return getInputValue(driver, usernameInputText);
    }

    // Input Password
    public static String inputPassword(WebDriver driver, String password) {
        sendKeys(driver, passwordInputText, password);
        return getInputValue(driver, passwordInputText);
    }

    // Click Submit Button
    public static boolean clickSubmitButton(WebDriver driver) {
        try {
            click(driver, submitButton);
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    // Get Displayed Username
    public static String getDisplayedUsername(WebDriver driver) {
        String rawText = getText(driver, userID);
        if (rawText.startsWith("Welcome ")) {
            rawText = rawText.replace("Welcome ", "");
        }
        return rawText;
    }

    public static String getAlertText(WebDriver driver) {
        return BasePage.getAlertText(driver);
    }
}
