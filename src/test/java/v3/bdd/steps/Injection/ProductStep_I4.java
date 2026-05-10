package v3.bdd.steps.Injection;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import v3.bdd.steps.BaseStep;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductStep_I4 extends BaseStep {

    @When("the user click the product title {string} 1")
    public void theUserClickTheProductTitle(String title) {
        click(By.xpath("//a[contains(text(), '" + title + "')]"));
    }

    @Then("the product title should be {string} 1")
    public void theProductTitleShouldBe(String expectedTitle) {
        String actualTitle = getText(By.className("name"));
        assertEquals("Product title mismatch!", expectedTitle, actualTitle);
    }

    @And("the product price should be {string} 1")
    public void theProductPriceShouldBe(String expectedPrice) {
        String actualPrice = getText(By.className("price-container"));
        assertTrue("Product price not found! Expected: " + expectedPrice, actualPrice.contains(expectedPrice));
    }

    @And("the product description should contain {string} 1")
    public void theProductDescriptionShouldContain(String expectedDescription) {
        String actualDescription = getText(By.id("more-information"));
        assertTrue("Description content mismatch!", actualDescription.contains(expectedDescription));
    }

    @And("the user clicks the Add to cart button 1")
    public void theUserClicksTheAddToCartButton() {
        click(By.xpath("//a[text()='Add to cart']"));
    }

    @Then("Show Alert message {string} 1")
    public void showAlertMessage(String expectedMessage) {
        String actualMessage = getAlertText();
        assertEquals("Alert message mismatch!", expectedMessage, actualMessage);
    }
}
