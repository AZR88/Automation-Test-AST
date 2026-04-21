package v1.Linear;

import helper.WebHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static helper.WebHelper.driver;

public class CartTest {

    @BeforeMethod
    public void setup() {
        WebHelper.startDriver("chrome");
        driver.get("https://www.demoblaze.com/");
    }

    @Test(priority = 1)
    public void testVerifyPriceAndTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Samsung galaxy s6')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        driver.findElement(By.xpath("//a[text()='Cart']")).click();

        String actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tbody[@id='tbodyid']//tr[@class='success']/td[2]"))).getText();

        String actualPrice = driver.findElement(By.xpath("//tbody[@id='tbodyid']//tr/td[3]")).getText();

        Assert.assertEquals(actualTitle, "Samsung galaxy s6");
        Assert.assertEquals(actualPrice, "360");
    }

    @Test(priority = 2)
    public void testDeleteItemFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Samsung galaxy s6')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        driver.findElement(By.xpath("//a[text()='Cart']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Delete']"))).click();

        boolean isRemoved = wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//td[text()='Samsung galaxy s6']")));

        Assert.assertTrue(isRemoved, "Item masih muncul di cart setelah dihapus");
    }

    @Test(priority = 3)
    public void testVerifyTotalPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Add Samsung galaxy s6
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Samsung galaxy s6')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        driver.findElement(By.xpath("//li[@class='nav-item active']//a[@class='nav-link']")).click();

        // Add Nexus 6
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Nexus 6')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        driver.findElement(By.xpath("//a[text()='Cart']")).click();

        List<WebElement> priceElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//tbody[@id='tbodyid']//tr/td[3]")));

        int totalCalculated = 0;
        for (WebElement el : priceElements) {
            String text = el.getText().replaceAll("[^0-9]", "");
            if (!text.isEmpty()) totalCalculated += Integer.parseInt(text);
        }

        String totalDisplayedText = driver.findElement(By.id("totalp")).getText();
        int totalDisplayed = Integer.parseInt(totalDisplayedText.replaceAll("[^0-9]", ""));

        Assert.assertEquals(totalDisplayed, 1010);
        Assert.assertEquals(totalCalculated, totalDisplayed);
    }

    @Test(priority = 4)
    public void testCompletePurchaseValid() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Samsung galaxy s6')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        driver.findElement(By.xpath("//a[text()='Cart']")).click();
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();

        driver.findElement(By.id("name")).sendKeys("Agus");
        driver.findElement(By.id("country")).sendKeys("USA");
        driver.findElement(By.id("city")).sendKeys("New York");
        driver.findElement(By.id("card")).sendKeys("1234567890123456");
        driver.findElement(By.id("month")).sendKeys("December");
        driver.findElement(By.id("year")).sendKeys("2025");

        driver.findElement(By.xpath("//button[text()='Purchase']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();
    }

    @Test(priority = 5)
    public void testCompletePurchaseInvalid() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Samsung galaxy s6')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        driver.findElement(By.xpath("//a[text()='Cart']")).click();
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();

        // Kosongkan field yang wajib
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("card")).clear();

        driver.findElement(By.xpath("//button[text()='Purchase']")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Please fill out Name and Creditcard.");
        alert.accept();
    }

    @AfterMethod
    public void tearDown() {
        WebHelper.tearDown();
    }
}