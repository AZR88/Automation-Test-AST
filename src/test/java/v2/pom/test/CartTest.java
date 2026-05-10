package v2.pom.test;

import helper.WebHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import v2.pom.page.Cart;
import v2.pom.page.Item;
import v2.pom.page.ProductPage;

import static helper.WebHelper.driver;

public class CartTest {

    @BeforeMethod
    public void setup() {
        WebHelper.startDriver("chrome");
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 1, description = "Scenario: Verify the price and title of a specific item in the cart")
    public void testVerifyPriceAndTitle() {
        Item.selectItemByName(driver, "Samsung galaxy s6");
        ProductPage.clickAdd(driver);
        Cart.verifyalert(driver);
        Item.clickCartButton(driver);

        Assert.assertEquals(Cart.checkTitle(driver), "Samsung galaxy s6", "Judul item tidak sesuai!");
        Assert.assertEquals(Cart.checkPrice(driver), "360", "Harga item tidak sesuai!");
    }

    @Test(priority = 2, description = "Scenario: Delete an item from the cart")
    public void testDeleteItemFromCart() {
        Item.selectItemByName(driver, "Samsung galaxy s6");
        ProductPage.clickAdd(driver);
        Cart.verifyalert(driver);
        Item.clickCartButton(driver);

        Cart.delete(driver);
        boolean isRemoved = Cart.theItemShouldNoLongerBeDisplayedInTheCart(driver, "Samsung galaxy s6");
        Assert.assertTrue(isRemoved, "Item 'Samsung galaxy s6' masih terlihat setelah dihapus!");
    }

    @Test(priority = 3, description = "Scenario: Verify the total price in the cart")
    public void testVerifyTotalPrice() {
        // Tambah item 1
        Item.selectItemByName(driver, "Samsung galaxy s6");
        ProductPage.clickAdd(driver);
        Cart.verifyalert(driver);
        Item.clickHomebutton(driver);

        // Tambah item 2
        Item.selectItemByName(driver, "Nexus 6");
        ProductPage.clickAdd(driver);
        Cart.verifyalert(driver);

        Item.clickCartButton(driver);

        int[] prices = Cart.calculateTotalPrice(driver);

        Assert.assertEquals(prices[1], 1010, "Total harga di UI tidak sesuai!");
        Assert.assertEquals(prices[0], prices[1], "Kalkulasi manual tidak cocok dengan UI!");
    }

    @Test(priority = 4, description = "Scenario: Complete a purchase with valid order details")
    public void testCompletePurchaseValid() {
        Item.selectItemByName(driver, "Samsung galaxy s6");
        ProductPage.clickAdd(driver);
        Cart.verifyalert(driver);
        Item.clickCartButton(driver);

        Cart.order(driver);

        Cart.fillField(driver, "name", "Agus");
        Cart.fillField(driver, "country", "USA");
        Cart.fillField(driver, "city", "New York");
        Cart.fillField(driver, "card", "1234567890123456");
        Cart.fillField(driver, "month", "December");
        Cart.fillField(driver, "year", "2025");

        Cart.purchase(driver);


        boolean isConfirmed = Cart.confirmation(driver);
        Assert.assertTrue(isConfirmed, "Gagal mengklik tombol OK pada konfirmasi pembelian!");
    }

    @Test(priority = 5, description = "Scenario: Complete a purchase with invalid order details")
    public void testCompletePurchaseInvalid() {
        Item.selectItemByName(driver, "Samsung galaxy s6");
        ProductPage.clickAdd(driver);
        Cart.verifyalert(driver);
        Item.clickCartButton(driver);

        Cart.order(driver);

        Cart.fillField(driver, "name", "");
        Cart.fillField(driver, "country", "USA");
        Cart.fillField(driver, "city", "");
        Cart.fillField(driver, "card", "");
        Cart.fillField(driver, "month", "December");
        Cart.fillField(driver, "year", "2025");

        Cart.purchase(driver);

        String alertMsg = Cart.getAlertText(driver);
        Assert.assertEquals(alertMsg, "Please fill out Name and Creditcard.", "Pesan alert salah!");
    }

    @AfterMethod
    public void tearDown() {
        WebHelper.tearDown();
    }
}