package v4.bddpom.page;

import helper.WaitElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helper.WebHelper.driver;

public abstract class BasePage {

    protected final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    protected void click(By locator) {
        WaitElement.waitForElement(locator);
        driver.findElement(locator).click();
    }

    protected void sendKeys(By locator, String value) {
        WaitElement.waitForElement(locator);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected String getText(By locator) {
        WaitElement.waitForElement(locator);
        return driver.findElement(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        try {
            WaitElement.waitForElement(locator);
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected String getAlertText() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String text = alert.getText();
            alert.accept();
            return text;
        } catch (TimeoutException e) {
            return null;
        }
    }

    protected void acceptAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (TimeoutException ignored) {}
    }
}