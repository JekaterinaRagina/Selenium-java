package lv.acodemy.page_object;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    public CartPage(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "cart_item")
    List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public WebElement getCheckoutButton() {
        return checkoutButton;

            }
}
