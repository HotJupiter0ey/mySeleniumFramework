import org.selenium.training.base.BaseTest;
import org.selenium.training.pages.CartPage;
import org.selenium.training.pages.CheckoutCompletePage;
import org.selenium.training.pages.CheckoutStepOnePage;
import org.selenium.training.pages.CheckoutStepTwoPage;
import org.selenium.training.pages.InventoryPage;
import org.selenium.training.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SaucedemoTest extends BaseTest {
    String username;
    String password;

    @BeforeMethod
    public void setup() {
        username = "standard_user";
        password = "secret_sauce";

        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.startPage();
        loginPage.Login(username, password);
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
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

    @Test(priority = 3)
    public void addItemsToCart_success() {
        log.info("Starting positive test for adding items to cart");
        InventoryPage inventoryPage = new InventoryPage(driver, log);
        inventoryPage.startPage();
        inventoryPage.addItemsToTheCart();
        Assert.assertTrue(inventoryPage.areButtonsChangeDisplayedNameToRemove());
        Assert.assertTrue(inventoryPage.shoppingCartBadgeCountEqualsTo(2));
    }

    @Test(priority = 4)
    public void deleteItemFromTheCartListPositiveTest() {
        log.info("Starting positive test for item deleting from the cart list");
        InventoryPage inventoryPage = new InventoryPage(driver, log);
        inventoryPage.startPage();
        inventoryPage.addItemsToTheCart();
        CartPage cartPage = new CartPage(driver, log);
        cartPage.startPage();
        cartPage.removeLabsBackpackFromTheCart();
        Assert.assertFalse(cartPage.isBackPackDeleted());
        Assert.assertTrue(cartPage.isShoppingCartBadgeCountEquals1());
        cartPage.clickContinueShoppingButton();
        inventoryPage.startPage();
        Assert.assertTrue(inventoryPage.isSauceLabsBackpackButtonDisplayed());
        Assert.assertTrue(inventoryPage.shoppingCartBadgeCountEqualsTo(1));

    }

    @Test(priority = 5)
    public void fullBuyingProcessTest() {
        log.info("Starting full buying process test");
        InventoryPage inventoryPage = new InventoryPage(driver, log);
        inventoryPage.startPage();
        inventoryPage.addItemsToTheCart();
        CartPage cartPage = new CartPage(driver, log);
        cartPage.startPage();
        cartPage.clickCheckoutButton();
        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(driver, log);
        checkoutStepOnePage.startPage();
        String firstname = "Jon";
        String lastname = "Wilson";
        String postalCode = "123123";
        checkoutStepOnePage.clickContinue(firstname, lastname, postalCode);
        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(driver, log);
        checkoutStepTwoPage.startPage();
        Assert.assertTrue(checkoutStepTwoPage.checkIfItemsInTheInventoryList());
        double total = 43.18d;
        Assert.assertTrue(checkoutStepTwoPage.checkTotalIsEqualTo(total));
        checkoutStepTwoPage.clickFinishButton();
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver, log);
        checkoutCompletePage.startPage();
        Assert.assertTrue(checkoutCompletePage.ifOrderCompleted());
        checkoutCompletePage.clickBackHomeButton();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
    }
}
