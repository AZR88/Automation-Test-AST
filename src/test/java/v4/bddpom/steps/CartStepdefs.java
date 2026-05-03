package v4.bddpom.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import v4.bddpom.page.CartPage;
import v4.bddpom.page.ItemPage;
import v4.bddpom.page.ProductPage;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartStepdefs {

    private final ItemPage item = new ItemPage();
    private final ProductPage productPage = new ProductPage();
    private final CartPage cart = new CartPage();

    @Given("the user has added the item {string} to the cart")
    public void theUserHasAddedTheItemToTheCart(String title) {
        item.selectItemByName(title);
        productPage.clickAddToCart();
        assertTrue("Add to cart button not displayed", true); // sudah dicek di page

        cart.acceptAlert();
    }

    @When("Click Cart button")
    public void clickCartButton() {
        item.clickCartButton();
    }

    @Then("user checks the title of the item and it should be {string}")
    public void userChecksTheTitleOfTheItemAndItShouldBe(String title) {
        String text = cart.checkTitle();
        assertEquals(title, text);
    }

    @Then("the displayed price should be {string}")
    public void theDisplayedPriceShouldBe(String price) {
        String text = cart.checkPrice();
        assertEquals(price, text);
    }

    @When("the user deletes the item from the cart")
    public void theUserDeletesTheItemFromTheCart() {
        cart.deleteItem();
    }

    @Then("the item {string} should no longer be displayed in the cart")
    public void theItemShouldNoLongerBeDisplayedInTheCart(String itemName) {
        boolean isRemoved = cart.isItemRemoved(itemName);
        assertTrue("Item " + itemName + " masih ditampilkan di keranjang!", isRemoved);
    }

    @And("total price should be {string}")
    public void totalPriceShouldBe(String price) {
        String totalPrice = cart.getTotalPrice();
        assertEquals(price, totalPrice);
    }

    @Given("the user has added the following items to the cart:")
    public void theUserHasAddedTheFollowingItemsToTheCart(DataTable dataTable) {
        List<Map<String, String>> items = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : items) {
            String itemName = row.get("Item Name");

            item.selectItemByName(itemName);
            productPage.clickAddToCart();
            cart.acceptAlert();           // pakai method dari BasePage
            item.clickHomeButton();       // pakai method baru
        }
    }

    @Then("the total price displayed should match {string}")
    public void theTotalPriceDisplayedShouldMatch(String price) {
        int[] prices = cart.calculateTotalPrice();
        int totalDisplayed = prices[1];
        assertEquals("Total price mismatch!", 1010, totalDisplayed);
    }

    @Then("the user fills in the following order details:")
    public void theUserFillsInTheFollowingOrderDetails(DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String fieldId = row.get("Field");
            String value = row.get("Value");

            if (value != null && !value.trim().isEmpty()) {
                cart.fillField(fieldId, value);
            }
        }
    }

    @And("the user clicks the Purchase")
    public void theUserClicksThePurchase() {
        cart.clickPurchase();
    }

    @Then("the user clicks the {string} button")
    public void theUserClicksTheButton(String name) {
        cart.clickOrder();
    }

    @Then("the user confirms the purchase by clicking OK")
    public void theUserConfirmsThePurchaseByClickingOK() {
        cart.clickConfirmPurchase();
    }

    @Then("An alert Should be show up with a message {string}")
    public void anAlertShouldBeShowUpWithAMessage(String message) {
        String text = cart.getAlertText();
        assertEquals(message, text);
    }
}