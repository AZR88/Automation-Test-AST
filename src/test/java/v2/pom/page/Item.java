package v2.pom.page;

import helper.WaitElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Item extends BasePage {
    private static By homeButton = By.xpath("//li[@class='nav-item active']//a[@class='nav-link']");
    private static By cartButton = By.xpath("(//a[normalize-space()='Cart'])");



    public static boolean clickCartButton(WebDriver driver){
        try {
            click(driver, cartButton);
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }

    public static boolean clickHomebutton(WebDriver driver){
        try {
            click(driver, homeButton);
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }


    public static boolean selectItemByName(WebDriver driver, String itemName) {
        By itemLocator = By.xpath("//a[contains(text(), '" + itemName + "')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            WebElement itemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator));
            itemElement.click();
            return true;
        } catch (TimeoutException|NoSuchElementException e ){
            return false;
        }
    }



}
