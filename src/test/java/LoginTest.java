import org.selenium.training.base.BaseTest;
import org.selenium.training.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    String username;
    String password;

    @BeforeMethod
    public void setup() {
        username = "standard_user";
        password = "secret_sauce";
    }

    @Test(priority = 1)
    public void loginTest_badLogin_incorrectPassword() {
        log.info("Starting negative login test");

        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.startPage();
        loginPage.Login(username, "incorrectPassword");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 2)
    public void loginTest_goodLogin() {
        log.info("Starting positive login test");

        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.startPage();
        loginPage.Login(username, password);
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
    }
}
