package lv.acodemy.page_object;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    public CheckoutPage(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement formSubmitButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(xpath = "//h2[@data-test='complete-header']")
    private WebElement completeOrderHeader;

    @FindBy(xpath = "//div[@data-test='complete-text']")
    private WebElement completeOrderText;

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
        formSubmitButton.click();
    }

    public void completePurchase() {
        finishButton.click();
    }
    public WebElement getCompleteOrderHeader() {
        return completeOrderHeader;
    }

    public WebElement getCompleteOrderText() {
        return completeOrderText;
    }
}
