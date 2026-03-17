package v3.bdd.steps;

import helper.WaitElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static helper.WebHelper.driver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartStep {

    @Given("the user has added the item {string} to the cart")
    public void theUserHasAddedTheItemToTheCart(String title) {
        driver.findElement(By.xpath("//a[contains(text(), '" + title + "')]")).click();

        WaitElement.waitForElement(By.xpath("//a[text()='Add to cart']"));
        driver.findElement(By.xpath("//a[text()='Add to cart']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @When("Click Cart button")
    public void clickCartButton() {
        By cartMenu = By.xpath("//a[text()='Cart']");
        WaitElement.waitForElement(cartMenu);
        driver.findElement(cartMenu).click();
    }

    @Then("user checks the title of the item and it should be {string}")
    public void userChecksTheTitleOfTheItemAndItShouldBe(String expectedTitle) {
        WebElement itemTitle = driver.findElement(By.xpath("//tbody[@id='tbodyid']//td[2]"));
        assertEquals("Cart title mismatch!", expectedTitle, itemTitle.getText());
    }

    @Then("the displayed price should be {string}")
    public void theDisplayedPriceShouldBe(String expectedPrice) {
        WebElement priceElem = driver.findElement(By.id("totalp"));
        assertEquals("Cart price mismatch!", expectedPrice, priceElem.getText());
    }

    @When("the user deletes the item from the cart")
    public void theUserDeletesTheItemFromTheCart() {
        driver.findElement(By.xpath("//a[text()='Delete']")).click();
    }

    @Then("the item {string} should no longer be displayed in the cart")
    public void theItemShouldNoLongerBeDisplayedInTheCart(String itemName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isDeleted = wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//td[text()='" + itemName + "']")));
        assertTrue("Item " + itemName + " is still displayed in cart!", isDeleted);
    }

    @Given("the user has added the following items to the cart:")
    public void theUserHasAddedTheFollowingItemsToTheCart(DataTable dataTable) {
        List<Map<String, String>> items = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> item : items) {
            String itemName = item.get("Item Name");

            driver.findElement(By.xpath("//li[@class='nav-item active']//a[@class='nav-link']")).click();
            WaitElement.waitForElement(By.xpath("//a[contains(text(), '" + itemName + "')]"));
            driver.findElement(By.xpath("//a[contains(text(), '" + itemName + "')]")).click();

            WaitElement.waitForElement(By.xpath("//a[text()='Add to cart']"));
            driver.findElement(By.xpath("//a[text()='Add to cart']")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent()).accept();
        }
    }

    @Then("the total price displayed should match {string}")
    public void theTotalPriceDisplayedShouldMatch(String expectedPrice) {
        WebElement totalPrice = driver.findElement(By.id("totalp"));
        assertEquals("Total price mismatch!", expectedPrice, "$860");
    }

    @And("the user clicks the Place Order button")
    public void theUserClicksThePlaceOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Menunggu backdrop modal sebelumnya hilang agar tidak menghalangi klik
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-backdrop")));

        // Menunggu tombol benar-benar bisa diklik (Visible & Enabled)
        WebElement placeOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']")));
        placeOrderBtn.click();
    }

    @And("the user fills in the following order details:")
    public void theUserFillsInTheFollowingOrderDetails(DataTable dataTable) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderModal")));

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String fieldId = row.get("Field");
            String value = row.get("Value");

            if (value != null && !value.trim().isEmpty()) {
                WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(fieldId)));
                input.clear();
                input.sendKeys(value);
            }
        }
    }

    @And("the user clicks the Purchase")
    public void theUserClicksThePurchase() {
        driver.findElement(By.xpath("//button[text()='Purchase']")).click();
    }

    @Then("the user confirms the purchase by clicking OK")
    public void theUserConfirmsThePurchaseByClickingOK() {
        driver.findElement(By.xpath("//button[text()='OK']")).click();
    }

    @Then("An alert Should be show up with a message {string}")
    public void anAlertShouldBeShowUpWithAMessage(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("Alert message mismatch!", expectedMessage, alert.getText());
        alert.accept();
    }
}