package org.selenium.training.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.Objects;


public class CartPage extends BasePage{
    public CartPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private final By removeBackpackButtonLocator = By.id("remove-sauce-labs-backpack");
    private final By backpackLocator = By.id("item_4_title_link");
    private final By shoppingCartBadgeLocator = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");
    private final By continueShoppingButtonLocator = By.id("continue-shopping");
    private final By checkoutButtonLocator = By.id("checkout");


    public void startPage() {
        openUrl("https://www.saucedemo.com/cart.html");
    }

    public void removeLabsBackpackFromTheCart() {
        click(removeBackpackButtonLocator);
    }

    public void clickContinueShoppingButton() {
        click(continueShoppingButtonLocator);
    }
    public void clickCheckoutButton() {
        click(checkoutButtonLocator);
    }
    public boolean isBackPackDeleted() {
        try {
            return find(backpackLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isShoppingCartBadgeCountEquals1() {
        try {
            return Objects.equals(find(shoppingCartBadgeLocator).getText(), "1");
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
