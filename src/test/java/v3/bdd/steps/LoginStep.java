package v3.bdd.steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static helper.WebHelper.driver;

public class LoginStep {

    @Given("user is on homepage {string}")
    public void userIsOnHomepage(String expectedUrl) {
        // Verifikasi URL sebagai pintu masuk tetap dipertahankan
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isUrlCorrect = wait.until(ExpectedConditions.urlToBe(expectedUrl));
        Assert.assertTrue("Incorrect URL!", isUrlCorrect);
    }

    @When("user click Login button")
    public void userClicksLoginButton() {
        driver.findElement(By.id("login2")).click();
    }

    @And("user input username with {string}")
    public void userInputsUsernameWith(String username) {
        // Locator tertulis langsung di sini (Hardcoded)
        WebElement userField = driver.findElement(By.id("loginusername"));
        userField.clear();
        userField.sendKeys(username);
    }

    @And("user input password with {string}")
    public void userInputsPasswordWith(String password) {
        // Locator tertulis langsung (Hardcoded)
        WebElement passField = driver.findElement(By.xpath("//*[@id='loginpassword']"));
        passField.clear();
        passField.sendKeys(password);
    }

    @And("user click submit")
    public void userClicksSubmitButton() {
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
    }

    @Then("user redirect to home page with {string} username displayed")
    public void verifyUsername(String expectedUsername) {
        // Verifikasi hasil akhir (Outcome) tetap menggunakan Assert
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement userElem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        Assert.assertTrue("Username mismatch!", userElem.getText().contains(expectedUsername));
    }
}