package v4.bddpom.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import v4.bddpom.page.Item;
import v4.bddpom.page.ProductPage;

import static helper.WebHelper.driver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductStepDef {

    @When("the user click the product title {string}")
    public void theUserClickTheProductTitle(String title) {
        Item.selectItemByName(driver, title);
    }

    @Then("the product title should be {string}")
    public void theProductTitleShouldBe(String expectedTitle) {
        String actualTitle = ProductPage.checkTitle(driver);
        assertEquals("Product title mismatch!", expectedTitle, actualTitle);
    }

    @And("the product price should be {string}")
    public void theProductPriceShouldBe(String expectedPrice) {
        String actualPrice = ProductPage.checkPrice(driver);
        assertEquals("Price mismatch!", expectedPrice, actualPrice);
    }

    @And("the product image should be displayed")
    public void theProductImageShouldBeDisplayed() {
        boolean isDisplayed = ProductPage.checkPic(driver);
        assertTrue("Product image is not displayed!", isDisplayed);
    }

    @And("the product description should contain {string}")
    public void theProductDescriptionShouldContain(String expectedDesc) {
        boolean isContained = ProductPage.isProductDescriptionDisplayed(driver, expectedDesc);
        assertTrue("Description does not contain: " + expectedDesc, isContained);
    }

    @And("the user clicks the Add to cart button")
    public void theUserClicksTheAddToCartButton() {
        ProductPage.clickAdd(driver);
    }

    @Then("Show Alert message {string}")
    public void showAlertMessage(String expectedMessage) {
        String actualMessage = ProductPage.getAlertText(driver);
        assertEquals("Alert message mismatch!", expectedMessage, actualMessage);
    }
}