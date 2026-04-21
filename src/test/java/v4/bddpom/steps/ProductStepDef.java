package v4.bddpom.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import v4.bddpom.page.ItemPage;
import v4.bddpom.page.ProductPage;

import static helper.WebHelper.driver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductStepDef {

    private final ItemPage item = new ItemPage();
    private final ProductPage productPage = new ProductPage();

    @When("the user click the product title {string}")
    public void theUserClickTheProductTitle(String title) {
        item.selectItemByName(title);
    }

    @Then("the product title should be {string}")
    public void theProductTitleShouldBe(String expectedTitle) {
        String actualTitle = productPage.checkTitle();
        assertEquals("Product title mismatch!", expectedTitle, actualTitle);
    }

    @And("the product price should be {string}")
    public void theProductPriceShouldBe(String expectedPrice) {
        String actualPrice = productPage.checkPrice();
        assertEquals("Price mismatch!", expectedPrice, actualPrice);
    }

    @And("the product image should be displayed")
    public void theProductImageShouldBeDisplayed() {
        boolean isDisplayed = productPage.checkPic();
        assertTrue("Product image is not displayed!", isDisplayed);
    }

    @And("the product description should contain {string}")
    public void theProductDescriptionShouldContain(String expectedDesc) {
        boolean isContained = productPage.isProductDescriptionDisplayed(expectedDesc);
        assertTrue("Description does not contain: " + expectedDesc, isContained);
    }

    @And("the user clicks the Add to cart button")
    public void theUserClicksTheAddToCartButton() {
        productPage.clickAddToCart();
    }

    @Then("Show Alert message {string}")
    public void showAlertMessage(String expectedMessage) {
        String actualMessage = productPage.getAlertText();
        assertEquals("Alert message mismatch!", expectedMessage, actualMessage);
    }
}