import com.github.javafaker.Faker;
import lv.acodemy.page_object.*;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.HashMap;
import java.util.Map;

public class SauceDemoTest {

    ChromeDriver driver;
    ChromeOptions options;

    LoginPage loginPage;
    InventoryPage inventoryPage;
    HeaderPage headerPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    private static final Logger logger = LoggerFactory.getLogger(SauceDemoTest.class);

    Faker data = new Faker();


    @BeforeMethod
    public void beforeTest() {

        options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        headerPage = new HeaderPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

    }

    @Test
    public void verifyLoggedInTest() {
        loginPage.authorize("standard_user", "secret_sauce");

        String productsText = driver.findElement(By.className("title")).getText();
        Assertions.assertThat(productsText)
                .withFailMessage("Are u lalka?", productsText)
                .isNotNull()
                .isNotEmpty()
                .startsWith("Prod")
                .endsWith("ucts")
                .isEqualTo("Products");

        Assert.assertEquals(productsText, "Products");
    }

    @Test
    public void logInTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorize("standard_user", "secret_sauce");
    }

    @Test
    public void addItemToCartTest() {
        loginPage.authorize("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemToCartByName("Onesie");
        Assertions.assertThat(headerPage.getCartBadgeText()).isEqualTo("1");

        headerPage.getShoppingCartLink().click();
        Assertions.assertThat(cartPage.getCartItems().size()).isEqualTo("1");

        cartPage.getCheckoutButton().click();

        String firstName = data.name().firstName();
        String lastName = data.name().lastName();
        String postalCode = data.address().zipCode();
    }

    @AfterMethod()
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
