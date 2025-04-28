import lv.acodemy.page_object.InventoryPage;
import lv.acodemy.page_object.LoginPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class SauceDemoTest {

    ChromeDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;


    @BeforeMethod
    public void beforeTest() {

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

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
    }

    @AfterMethod()
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
