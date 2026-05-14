package v2.pom.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // Locators
    public static By loginButton = By.id("login2");
    public static By usernameInputText = By.cssSelector("#loginusername");
    public static By passwordInputText = By.xpath("//*[@id='loginpassword']");
    public static By submitButton = By.xpath("//button[text()='Log in']");
    public static By userID = By.xpath("//a[@id='nameofuser']");


    public static boolean isUrlCorrect(WebDriver driver, String expectedUrl) {
        try {
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public static boolean clickLoginButton(WebDriver driver) {
        try {
            click(driver, loginButton);
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }


    public static String inputUsername(WebDriver driver, String username) {
        sendKeys(driver, usernameInputText, username);
        return getInputValue(driver, usernameInputText);
    }


    public static String inputPassword(WebDriver driver, String password) {
        sendKeys(driver, passwordInputText, password);
        return getInputValue(driver, passwordInputText);
    }


    public static boolean clickSubmitButton(WebDriver driver) {
        try {
            click(driver, submitButton);
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }


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
