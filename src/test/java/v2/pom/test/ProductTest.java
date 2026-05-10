package v2.pom.test;

import helper.WebHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import v2.pom.page.Item;
import v2.pom.page.ProductPage;

import static helper.WebHelper.driver;

public class ProductTest {

    @BeforeMethod
    public void setup() {
        WebHelper.startDriver("chrome");
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 1, description = "Scenario: Verify the product title, price, image, and description of a specific item")
    public void testVerifySpecificProductDetail() {
        WebDriver localDriver = driver;

        // When the user click the product title "Samsung galaxy s6"
        Assert.assertTrue(Item.selectItemByName(localDriver, "Samsung galaxy s6"), "Gagal membuka product Samsung galaxy s6");

        // Then the product title should be "Samsung galaxy s6"
        Assert.assertEquals(ProductPage.checkTitle(localDriver), "Samsung galaxy s6", "Product title mismatch!");

        // And the product price should be "$360"
        Assert.assertEquals(ProductPage.checkPrice(localDriver), "360", "Product price mismatch!");

        // And the product image should be displayed
        Assert.assertTrue(ProductPage.checkPic(localDriver), "Product image is not displayed");

        // And the product description should contain "Samsung Galaxy S6"
        Assert.assertTrue(
                ProductPage.isProductDescriptionDisplayed(localDriver, "Samsung Galaxy S6"),
                "Product description does not contain expected text"
        );
    }

    @Test(priority = 2, description = "Scenario: Add a specific product to the cart and verify alert")
    public void testAddSpecificProductToCartShowsAlert() {
        WebDriver localDriver = driver;

        Assert.assertTrue(Item.selectItemByName(localDriver, "Samsung galaxy s6"), "Gagal membuka product Samsung galaxy s6");
        Assert.assertTrue(ProductPage.clickAdd(localDriver), "Gagal klik tombol Add to cart");

        String alertMsg = ProductPage.getAlertText(localDriver);
        Assert.assertEquals(alertMsg, "Product added", "Alert message mismatch!");
    }

    @AfterMethod
    public void tearDown() {
        WebHelper.tearDown();
    }
}
