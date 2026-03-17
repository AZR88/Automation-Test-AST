package v4.bddpom.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import v4.bddpom.page.LoginPage;

import static helper.WebHelper.driver;

public class LoginStep {

    @Given("user is on homepage {string}")
    public void userIsOnHomepage(String expectedUrl) {
        boolean isUrlCorrect = LoginPage.isUrlCorrect(driver, expectedUrl);
        Assert.assertTrue("Incorrect URL!", isUrlCorrect);
    }

    @When("user click Login button")
    public void userClicksLoginButton() {
        LoginPage.clickLoginButton(driver);
    }

    @And("user input username with {string}")
    public void userInputsUsernameWith(String username) {
        LoginPage.inputUsername(driver, username);
    }

    @And("user input password with {string}")
    public void userInputsPasswordWith(String password) {
        LoginPage.inputPassword(driver, password);
    }

    @And("user click submit")
    public void userClicksSubmitButton() {
        LoginPage.clickSubmitButton(driver);
    }

    @Then("user redirect to home page with {string} username displayed")
    public void userRedirectedToHomePageWithUsernameDisplayed(String expectedUsername) {
        String actualUsername = LoginPage.getDisplayedUsername(driver);
        Assert.assertEquals("Username is not equals to account!", expectedUsername, actualUsername);
    }

    @Then("show invalid login notification {string}")
    public void showInvalidLoginNotification(String expectedMessage) {
        String actualMessage = LoginPage.getAlertText(driver);
        Assert.assertEquals("Alert message mismatch!", expectedMessage, actualMessage);
    }
}