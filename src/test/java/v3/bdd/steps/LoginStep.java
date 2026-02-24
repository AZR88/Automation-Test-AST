package v3.bdd.steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;

public class LoginStep {

    @Given("user is on demoblaze homepage")
    public void userIsOnDemoblazeHomepage() {
        // Driver sudah diatur di Hooks untuk buka URL [cite: 142]
        System.out.println("At Homepage: " + Hooks.driver.getCurrentUrl());
    }

    @When("user clicks login menu")
    public void userClicksLoginMenu() {
        Hooks.driver.findElement(By.id("login2")).click();
    }

    @And("user enters username {string} and password {string}")
    public void userEntersUsernameAndPassword(String username, String password) {
        Hooks.driver.findElement(By.id("loginusername")).sendKeys(username);
        Hooks.driver.findElement(By.id("loginpassword")).sendKeys(password);
    }

    @And("user clicks login button")
    public void userClicksLoginButton() {
        Hooks.driver.findElement(By.xpath("//button[text()='Log in']")).click();
    }

    @Then("login should be successful")
    public void loginShouldBeSuccessful() {
        System.out.println("Login step executed.");
    }
}