package v2.pom.test;

import helper.WebHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import v2.pom.page.Cart_I6;
import v2.pom.page.Item;
import v2.pom.page.ProductPage;

import static helper.WebHelper.driver;

public class CartTest_I6 {

    @BeforeMethod
    public void setup() {
        WebHelper.startDriver("chrome");
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 1, description = "Scenario: Verify the price and title of a specific item in the cart")
    public void testVerifyPriceAndTitle() {
        Item.selectItemByName(driver, "Samsung galaxy s6");
        ProductPage.clickAdd(driver);
        Cart_I6.verifyalert(driver);
        Item.clickCartButton(driver);

        Assert.assertEquals(Cart_I6.checkTitle(driver), "Samsung galaxy s6", "Judul item tidak sesuai!");
        Assert.assertEquals(Cart_I6.checkPrice(driver), "360", "Harga item tidak sesuai!");
    }

    @Test(priority = 2, description = "Scenario: Delete an item from the cart")
    public void testDeleteItemFromCart() {
        Item.selectItemByName(driver, "Samsung galaxy s6");
        ProductPage.clickAdd(driver);
        Cart_I6.verifyalert(driver);
        Item.clickCartButton(driver);

        Cart_I6.delete(driver);
        boolean isRemoved = Cart_I6.theItemShouldNoLongerBeDisplayedInTheCart(driver, "Samsung galaxy s6");
        Assert.assertTrue(isRemoved, "Item 'Samsung galaxy s6' masih terlihat setelah dihapus!");
    }

    @Test(priority = 3, description = "Scenario: Verify the total price in the cart")
    public void testVerifyTotalPrice() {
        Item.selectItemByName(driver, "Samsung galaxy s6");
        ProductPage.clickAdd(driver);
        Cart_I6.verifyalert(driver);
        Item.clickHomebutton(driver);

        Item.selectItemByName(driver, "Nexus 6");
        ProductPage.clickAdd(driver);
        Cart_I6.verifyalert(driver);

        Item.clickCartButton(driver);

        int[] prices = Cart_I6.calculateTotalPrice(driver);
        Assert.assertEquals(prices[1], 1010, "Total harga di UI tidak sesuai!");
        Assert.assertEquals(prices[0], prices[1], "Kalkulasi manual tidak cocok dengan UI!");
    }

    @Test(priority = 4, description = "Scenario: Complete a purchase with valid order details")
    public void testCompletePurchaseValid() {
        Item.selectItemByName(driver, "Samsung galaxy s6");
        ProductPage.clickAdd(driver);
        Cart_I6.verifyalert(driver);
        Item.clickCartButton(driver);

        Cart_I6.order(driver);

        Cart_I6.fillField(driver, "name", "Agus");
        Cart_I6.fillField(driver, "country", "USA");
        Cart_I6.fillField(driver, "city", "New York");
        Cart_I6.fillField(driver, "card", "1234567890123456");
        Cart_I6.fillField(driver, "month", "December");
        Cart_I6.fillField(driver, "year", "2025");

        Cart_I6.inputPromo(driver, "DISC50");
        Cart_I6.purchase(driver);

        boolean isConfirmed = Cart_I6.confirmation(driver);
        Assert.assertTrue(isConfirmed, "Gagal mengklik tombol OK pada konfirmasi pembelian!");
    }

    @Test(priority = 5, description = "Scenario: Complete a purchase with invalid order details")
    public void testCompletePurchaseInvalid() {
        Item.selectItemByName(driver, "Samsung galaxy s6");
        ProductPage.clickAdd(driver);
        Cart_I6.verifyalert(driver);
        Item.clickCartButton(driver);

        Cart_I6.order(driver);

        Cart_I6.fillField(driver, "name", "");
        Cart_I6.fillField(driver, "country", "USA");
        Cart_I6.fillField(driver, "city", "");
        Cart_I6.fillField(driver, "card", "");
        Cart_I6.fillField(driver, "month", "December");
        Cart_I6.fillField(driver, "year", "2025");

        Cart_I6.inputPromo(driver, "DISC50");
        Cart_I6.purchase(driver);

        String alertMsg = Cart_I6.getAlertText(driver);
        Assert.assertEquals(alertMsg, "Please fill out Name and Creditcard.", "Pesan alert salah!");
    }

    @AfterMethod
    public void tearDown() {
        WebHelper.tearDown();
    }
}
