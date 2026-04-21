package v3.bdd.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartStep extends BaseStep {

    @Given("the user has added the item {string} to the cart")
    public void theUserHasAddedTheItemToTheCart(String title) {
        click(By.xpath("//a[contains(text(), '" + title + "')]"));
        click(By.xpath("//a[text()='Add to cart']"));
        acceptAlert();
    }

    @When("Click Cart button")
    public void clickCartButton() {
        click(By.xpath("//a[text()='Cart']"));
    }

    @Then("user checks the title of the item and it should be {string}")
    public void userChecksTheTitleOfTheItemAndItShouldBe(String expectedTitle) {
        String actualTitle = getText(By.xpath("//tbody[@id='tbodyid']//td[2]"));
        assertEquals(expectedTitle, actualTitle);
    }

    @Then("the displayed price should be {string}")
    public void theDisplayedPriceShouldBe(String expectedPrice) {
        String actualPrice = getText(By.id("totalp"));
        assertEquals(expectedPrice, actualPrice);
    }

    @When("the user deletes the item from the cart")
    public void theUserDeletesTheItemFromTheCart() {
        click(By.xpath("//a[text()='Delete']"));
    }

    @Then("the item {string} should no longer be displayed in the cart")
    public void theItemShouldNoLongerBeDisplayedInTheCart(String itemName) {
        By locator = By.xpath("//td[text()='" + itemName + "']");
        boolean isDeleted = isElementInvisible(locator);
        assertTrue("Item " + itemName + " is still displayed in cart!", isDeleted);
    }

    @Given("the user has added the following items to the cart:")
    public void theUserHasAddedTheFollowingItemsToTheCart(DataTable dataTable) {
        List<Map<String, String>> items = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : items) {
            String itemName = row.get("Item Name");

            click(By.xpath("//li[@class='nav-item active']//a[@class='nav-link']")); // home
            click(By.xpath("//a[contains(text(), '" + itemName + "')]"));
            click(By.xpath("//a[text()='Add to cart']"));
            acceptAlert();
        }
    }

    @Then("the total price displayed should match {string}")
    public void theTotalPriceDisplayedShouldMatch(String expectedPrice) {
        String totalPrice = getText(By.id("totalp"));
        assertEquals("Total price mismatch!", expectedPrice, totalPrice);
    }

    @And("the user clicks the Place Order button")
    public void theUserClicksThePlaceOrderButton() {
        click(By.xpath("//button[text()='Place Order']"));
    }

    @And("the user fills in the following order details:")
    public void theUserFillsInTheFollowingOrderDetails(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String fieldId = row.get("Field");
            String value = row.get("Value");
            if (value != null && !value.trim().isEmpty()) {
                sendKeys(By.id(fieldId), value);
            }
        }
    }

    @And("the user clicks the Purchase")
    public void theUserClicksThePurchase() {
        click(By.xpath("//button[text()='Purchase']"));
    }

    @Then("the user confirms the purchase by clicking OK")
    public void theUserConfirmsThePurchaseByClickingOK() {
        click(By.xpath("//button[text()='OK']"));
    }

    @Then("An alert Should be show up with a message {string}")
    public void anAlertShouldBeShowUpWithAMessage(String expectedMessage) {
        String actual = getAlertText();
        assertEquals(expectedMessage, actual);
    }
}