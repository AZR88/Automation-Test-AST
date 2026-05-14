package v3.bdd.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginStep extends BaseStep {

    @Given("user is on homepage {string}")
    public void userIsOnHomepage(String expectedUrl) {
        boolean isUrlCorrect = wait.until(ExpectedConditions.urlToBe(expectedUrl));
        Assert.assertTrue("Incorrect URL!", isUrlCorrect);
    }

    @When("user click Login button")
    public void userClicksLoginButton() {
        click(By.id("login2"));
    }

    @And("user input username with {string}")
    public void userInputsUsernameWith(String username) {
        sendKeys(By.id("loginusername"), username);
    }

    @And("user input password with {string}")
    public void userInputsPasswordWith(String password) {
        sendKeys(By.xpath("//*[@id='loginpassword']"), password);
    }

    @And("user click submit")
    public void userClicksSubmitButton() {
        click(By.xpath("//button[text()='Log in']"));
    }

    @Then("user redirect to home page with {string} username displayed")
    public void userRedirectedToHomePageWithUsernameDisplayed(String expectedUsername) {
        String actual = getText(By.id("nameofuser")).replace("Welcome ", "");
        Assert.assertEquals("Username mismatch!", expectedUsername, actual);
    }

    @Then("show invalid login notification {string}")
    public void showInvalidLoginNotification(String expectedMessage) {
        String actualMessage = getAlertText();
        Assert.assertEquals("Alert message mismatch!", expectedMessage, actualMessage);
    }
}
