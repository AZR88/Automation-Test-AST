package v4.bddpom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage_I1 extends BasePage {

    private static final By LOGIN_BUTTON = By.id("login2");
    private static final By USERNAME_INPUT = By.cssSelector("#user_v2");
    private static final By PASSWORD_INPUT = By.xpath("//*[@id='loginpassword']");
    private static final By SUBMIT_BUTTON = By.xpath("//button[text()='Log in']");
    private static final By USER_ID = By.id("nameofuser");

    public boolean isUrlCorrect(WebDriver driver, String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLoginButton() {
        click(LOGIN_BUTTON);
    }

    public void inputUsername(String username) {
        sendKeys(USERNAME_INPUT, username);
    }

    public void inputPassword(String password) {
        sendKeys(PASSWORD_INPUT, password);
    }

    public void clickSubmitButton() {
        click(SUBMIT_BUTTON);
    }

    public String getDisplayedUsername() {
        String text = getText(USER_ID);
        return text.replace("Welcome ", "");
    }

    public String getAlertText() {
        return super.getAlertText();
    }
}
