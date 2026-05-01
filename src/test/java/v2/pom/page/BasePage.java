package v2.pom.page;

import helper.WaitElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected static void click(WebDriver driver, By locator) {
        WaitElement.waitForElement(locator);
        driver.findElement(locator).click();
    }

    protected static void sendKeys(WebDriver driver, By locator, String value) {
        WaitElement.waitForElement(locator);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected static String getText(WebDriver driver, By locator) {
        WaitElement.waitForElement(locator);
        return driver.findElement(locator).getText().trim();
    }

    protected static boolean isDisplayed(WebDriver driver, By locator) {
        try {
            WaitElement.waitForElement(locator);
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected static String getInputValue(WebDriver driver, By locator) {
        WaitElement.waitForElement(locator);
        return driver.findElement(locator).getAttribute("value");
    }

    protected static String getAlertText(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String text = alert.getText();
            alert.accept();
            return text;
        } catch (TimeoutException e) {
            return null;
        }
    }

    protected static boolean acceptAlert(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
