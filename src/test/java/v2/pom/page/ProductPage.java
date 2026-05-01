package v2.pom.page;

import helper.WaitElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    public static By productPic = By.xpath("//div[@id='imgp']//img");
    public static By productTitle = By.xpath("//h2[@class='name']");
    public static By productPrice = By.xpath("//div[@id='tbodyid']/h3[@class='price-container']");
    public static By addToCart = By.xpath("//a[@class='btn btn-success btn-lg' and text()='Add to cart']");

    public static boolean isProductDescriptionDisplayed(WebDriver driver, String productdesc) {
        By descLocator = By.xpath("//p[contains(text(),'" + productdesc + "')]");
        try {
            WaitElement.waitForElement(descLocator);
            WebElement productDescriptionElement = driver.findElement(descLocator);
            return productDescriptionElement.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Element with product description '" + productdesc + "' not found or not visible.");
            return false;
        }
    }


    public static String  checkTitle(WebDriver driver) {
        return getText(driver, productTitle);
    }

    public static String checkPrice(WebDriver driver) {
        String text = getText(driver, productPrice);
        String actualPrice = text.split("\n")[0].replaceAll("[^\\d$.]", "").trim();
        return  actualPrice;
    }



    public static boolean checkPic(WebDriver driver){
        try {
            WaitElement.waitForElement(productPic);
            WebElement itemPic = driver.findElement(productPic);
            return itemPic.isDisplayed();
        } catch ( TimeoutException | NoSuchElementException e){
            return false;
        }
    }

    public static Boolean clickAdd (WebDriver driver){
                try {
                    click(driver, addToCart);
                    return true;
                }catch (TimeoutException | NoSuchElementException e){
                    return false;
                }
            }



    public static String getAlertText(WebDriver driver) {
        return BasePage.getAlertText(driver);
    }

}
