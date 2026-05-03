package v4.bddpom.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static helper.WebHelper.driver;

public class CartPage_I6 extends BasePage {

    private static final By CART_ITEM = By.xpath("//tbody[@id='tbodyid']//tr[@class='success']/td[2]");
    private static final By DELETE_ITEM = By.xpath("//tbody[@id='tbodyid']//tr[@class='success']//a[contains(text(),'Delete')]");
    private static final By TOTAL_PRICE = By.id("totalp");
    private static final By ORDER_BUTTON = By.xpath("//button[text()='Place Order']");
    private static final By PURCHASE_BUTTON = By.xpath("//button[text()='Purchase']");
    private static final By ALL_PRICE = By.xpath("//tbody[@id='tbodyid']//tr/td[3]");
    private static final By CONFIRM_PURCHASE = By.xpath("//div[@class='sa-confirm-button-container']");

    public void clickOrder() {
        click(ORDER_BUTTON);
    }

    public void clickPurchase() {
        click(PURCHASE_BUTTON);
    }

    public void clickConfirmPurchase() {
        click(CONFIRM_PURCHASE);
    }

    public void deleteItem() {
        click(DELETE_ITEM);
    }

    public String checkTitle() {
        return getText(CART_ITEM);
    }

    public String checkPrice() {
        return getText(ALL_PRICE);
    }

    public String getTotalPrice() {
        return getText(TOTAL_PRICE);
    }

    public void fillField(String fieldId, String value) {
        By locator = By.id(fieldId);
        if ("country".equals(fieldId)) {
            new Select(driver.findElement(locator)).selectByVisibleText(value);
        } else {
            sendKeys(locator, value);
        }
    }

    public boolean isItemRemoved(String itemName) {
        By itemLocator = By.xpath("//tbody[@id='tbodyid']//tr[td[2][text()='" + itemName + "']]");
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(itemLocator));
        } catch (Exception e) {
            return false;
        }
    }

    public int[] calculateTotalPrice() {
        List<WebElement> prices = driver.findElements(ALL_PRICE);
        int calculated = 0;

        for (WebElement el : prices) {
            String text = el.getText().trim().replaceAll("[^0-9]", "");
            if (!text.isEmpty()) calculated += Integer.parseInt(text);
        }

        int displayed = Integer.parseInt(getTotalPrice().replaceAll("[^0-9]", ""));
        return new int[]{calculated, displayed};
    }

    public String getAlertText() {
        return super.getAlertText();
    }

    public void acceptAlert() {
        super.acceptAlert();
    }
}
