package v4.bddpom.page;

import org.openqa.selenium.By;

public class ProductPage_I4 extends BasePage {

    private static final By PRODUCT_TITLE = By.xpath("//h2[@class='name']");
    private static final By PRODUCT_PRICE = By.xpath("//div[@id='tbodyid']/h3[@class='price-container']");
    private static final By ADD_TO_CART = By.xpath("//a[@class='btn btn-success btn-lg' and text()='Add to cart']");

    public String checkTitle() {
        return getText(PRODUCT_TITLE);
    }

    public String checkPrice() {
        String text = getText(PRODUCT_PRICE);
        return text.split("\n")[0].replaceAll("[^\\d$.]", "").trim();
    }

    public void clickAddToCart() {
        click(ADD_TO_CART);
    }

    public boolean isProductDescriptionDisplayed(String productDesc) {
        By descLocator = By.xpath("//p[contains(text(),'" + productDesc + "')]");
        return isDisplayed(descLocator);
    }

    public String getAlertText() {
        return super.getAlertText();
    }
}
