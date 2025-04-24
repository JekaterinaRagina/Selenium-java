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

    @BeforeMethod
    public void beforeTest() {
        // Chromedriver
        driver = new ChromeDriver();
        // How to call driver methods?
        // URL: www.saucedemo.com
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@data-test='login-button']")).click();
    }

    @Test
    public void verifyLoggedInTest() {
        String productsText = driver.findElement(By.className("title")).getText();
        Assertions.assertThat(productsText)
                .withFailMessage("Are u lalka?", productsText)
                .isNotEmpty()
                .isNotNull();

        Assert.assertEquals(productsText, "Products");
    }

    @AfterMethod()
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
