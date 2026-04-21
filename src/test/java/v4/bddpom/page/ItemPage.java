package v4.bddpom.page;

import org.openqa.selenium.By;

public class ItemPage extends BasePage {

    private static final By HOME_BUTTON = By.xpath("//li[@class='nav-item active']//a[@class='nav-link']");
    private static final By CART_BUTTON = By.xpath("(//a[normalize-space()='Cart'])");

    public void clickCartButton() {
        click(CART_BUTTON);
    }

    public void clickHomeButton() {
        click(HOME_BUTTON);
    }

    public void selectItemByName(String itemName) {
        By itemLocator = By.xpath("//a[contains(text(), '" + itemName + "')]");
        click(itemLocator);
    }
}