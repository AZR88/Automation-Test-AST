package v2.pom.page;

import helper.WaitElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class Cart_I6 extends BasePage {
    public static By cartitem = By.xpath("//tbody[@id='tbodyid']//tr[@class='success']/td[2]");
    public static By deleteItem = By.xpath("//tbody[@id='tbodyid']//tr[@class='success']//a[contains(text(),'Delete')]");
    public static By TotalPrice = By.id("totalp");
    public static By orderButton =  By.xpath("//button[text()='Place Order']");
    public static By purchasebutton = By.xpath("//button[text()='Purchase']");
    public static By promo = By.id("promo");
    public static By allPrice = By.xpath("//tbody[@id='tbodyid']//tr/td[3]");
    public static By confirmPurchase = By.xpath("//div[@class='sa-confirm-button-container']");

    public static boolean order(WebDriver driver){
        try {
            click(driver, orderButton);
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }

    }

    public static boolean confirmation(WebDriver driver){
        try {
            click(driver, confirmPurchase);
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }

    }

    public static String checkPrice(WebDriver driver){

        return getText(driver, allPrice);
    }

    public static String checkTitle(WebDriver driver){
        return getText(driver, cartitem);
    }

    public static boolean delete(WebDriver driver){
        try {
            click(driver, deleteItem);
            return true;
        }catch (TimeoutException|NoSuchElementException e){
            return false;
        }
    }

    public static boolean purchase (WebDriver driver){
            try {
                click(driver, purchasebutton);
                return true;
            } catch (TimeoutException| NoSuchElementException e){
                return false;
            }
    }

    public static boolean inputPromo(WebDriver driver, String promoCode) {
        try {
            sendKeys(driver, promo, promoCode);
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public static boolean fillField(WebDriver driver, String fieldId, String fieldValue) {
        try {
            if ("country".equals(fieldId)) {
                new Select(driver.findElement(By.id(fieldId))).selectByVisibleText(fieldValue);
            } else {
                sendKeys(driver, By.id(fieldId), fieldValue);
            }
            return true;
        } catch (TimeoutException| NoSuchElementException e){
            return false;
        }}

    public  static String  totalPrice (WebDriver driver)
    {

        return getText(driver, TotalPrice);
    }

    public static boolean theItemShouldNoLongerBeDisplayedInTheCart(WebDriver driver, String itemName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            By itemLocator = By.xpath("//tbody[@id='tbodyid']//tr[td[2][text()='" + itemName + "']]");
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(itemLocator));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int[] calculateTotalPrice(WebDriver driver) {
            WaitElement.waitForElement(allPrice);
            List<WebElement> prices = driver.findElements(allPrice);
            int totalPriceCalculated = 0;

            for (WebElement priceElement : prices) {
                String priceText = priceElement.getText().trim();
                System.out.println("Price Text: " + priceText);
                if (!priceText.isEmpty()) {
                    int price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
                    totalPriceCalculated += price;
                }
            }
            System.out.println("Total Price Calculated: " + totalPriceCalculated);

            WebElement totalPriceElement = driver.findElement(TotalPrice);
            String totalPriceText = totalPriceElement.getText().trim();
            int totalPriceDisplayed = Integer.parseInt(totalPriceText.replaceAll("[^0-9]", ""));

            return new int[] { totalPriceCalculated, totalPriceDisplayed };
        }

        public static boolean verifyalert (WebDriver driver) {
            return acceptAlert(driver);
        }

    public static String getAlertText(WebDriver driver) {
        return BasePage.getAlertText(driver);
    }

}
