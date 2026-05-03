package v3.bdd.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductStep extends BaseStep {

    @When("the user click the product title {string}")
    public void theUserClickTheProductTitle(String title) {
        click(By.xpath("//a[contains(text(), '" + title + "')]"));
    }

    @Then("the product title should be {string}")
    public void theProductTitleShouldBe(String expectedTitle) {
        String actualTitle = getText(By.className("name"));
        assertEquals("Product title mismatch!", expectedTitle, actualTitle);
    }

    @And("the product price should be {string}")
    public void theProductPriceShouldBe(String expectedPrice) {
        String actualPrice = getText(By.className("price-container"));
        assertTrue("Product price not found! Expected: " + expectedPrice, actualPrice.contains(expectedPrice));
    }

    @And("the product image should be displayed")
    public void theProductImageShouldBeDisplayed() {
        boolean isDisplayed =  isElementInvisible(By.xpath("//div[@class='item active']//img"));
        assertTrue("Product image is not displayed!", isDisplayed);
    }

    @And("the product description should contain {string}")
    public void theProductDescriptionShouldContain(String expectedDescription) {
        String actualDescription = getText(By.id("more-information"));
        assertTrue("Description content mismatch!", actualDescription.contains(expectedDescription));
    }

    @And("the user clicks the Add to cart button")
    public void theUserClicksTheAddToCartButton() {
        click(By.xpath("//a[text()='Add to cart']"));
    }

    @Then("Show Alert message {string}")
    public void showAlertMessage(String expectedMessage) {
        String actualMessage = getAlertText();
        assertEquals("Alert message mismatch!", expectedMessage, actualMessage);
    }
}