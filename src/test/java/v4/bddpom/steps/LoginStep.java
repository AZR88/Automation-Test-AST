package v4.bddpom.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import v4.bddpom.page.LoginPage;

import static helper.WebHelper.driver;

public class LoginStep {

    private final LoginPage loginPage = new LoginPage();

    @Given("user is on homepage {string}")
    public void userIsOnHomepage(String expectedUrl) {
        Assert.assertTrue("URL tidak sesuai!", loginPage.isUrlCorrect(expectedUrl));
    }

    @When("user click Login button")
    public void userClicksLoginButton() {
        loginPage.clickLoginButton();
    }

    @And("user input username with {string}")
    public void userInputsUsernameWith(String username) {
        loginPage.inputUsername(username);
    }

    @And("user input password with {string}")
    public void userInputsPasswordWith(String password) {
        loginPage.inputPassword(password);
    }

    @And("user click submit")
    public void userClicksSubmitButton() {
        loginPage.clickSubmitButton();
    }

    @Then("user redirect to home page with {string} username displayed")
    public void userRedirectedToHomePageWithUsernameDisplayed(String expectedUsername) {
        String actualUsername = loginPage.getDisplayedUsername();
        Assert.assertEquals("Username tidak sesuai!", expectedUsername, actualUsername);
    }

    @Then("show invalid login notification {string}")
    public void showInvalidLoginNotification(String expectedMessage) {
        String actualMessage = loginPage.getAlertText();
        Assert.assertEquals("Alert message tidak sesuai!", expectedMessage, actualMessage);
    }
}