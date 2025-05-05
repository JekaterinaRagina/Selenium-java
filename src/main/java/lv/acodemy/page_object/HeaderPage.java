package lv.acodemy.page_object;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {
    public HeaderPage(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    public WebElement shoppingCartLink;

    public String getCartBadgeText() {
        return cartBadge.getText();
    }

    public WebElement getShoppingCartLink() {
        return shoppingCartLink;
    }
}
