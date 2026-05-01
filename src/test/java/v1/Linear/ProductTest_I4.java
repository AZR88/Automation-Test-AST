package v1.Linear;

import helper.WaitElement;
import helper.WebHelper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static helper.WebHelper.driver;

public class ProductTest_I4 {

    @BeforeMethod
    public void setup() {
        WebHelper.startDriver("chrome");
        driver.get("https://www.demoblaze.com/");
    }

    @Test
    public void testVerifySpecificProductDetail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WaitElement.waitForElement(By.xpath("//a[contains(text(), 'Samsung galaxy s6')]"));
        driver.findElement(By.xpath("//a[contains(text(), 'Samsung galaxy s6')]")).click();

        String actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='name']"))).getText();
        String actualPriceRaw = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tbodyid']/h3[@class='price-container']"))).getText();
        String actualPrice = actualPriceRaw.split("\\n")[0].replaceAll("[^\\d$.]", "").trim();

        String actualDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Samsung Galaxy S6')]"))).getText();

        Assert.assertEquals(actualTitle, "Samsung galaxy s6");
        Assert.assertEquals(actualPrice, "$360");
        Assert.assertTrue(actualDescription.contains("Samsung Galaxy S6"), "Product description is not displayed correctly");
    }

    @Test
    public void testAddSpecificProductToCartShowsAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WaitElement.waitForElement(By.xpath("//a[contains(text(), 'Samsung galaxy s6')]"));
        driver.findElement(By.xpath("//a[contains(text(), 'Samsung galaxy s6')]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn btn-success btn-lg' and text()='Add to cart']"))).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Product added");
        alert.accept();
    }

    @AfterMethod
    public void tearDown() {
        WebHelper.tearDown();
    }
}
