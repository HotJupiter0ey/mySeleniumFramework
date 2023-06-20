package org.selenium.training.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class InventoryPage extends BasePage{
    public InventoryPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private final By sauceLabsBackpackButtonLocator = By.id("add-to-cart-sauce-labs-backpack");
    private final By sauceLabsBikeLightButtonLocator = By.id("add-to-cart-sauce-labs-bike-light");
    private final By sauceLabsBackpackRemoveButtonLocator = By.id("remove-sauce-labs-backpack");
    private final By sauceLabsBikeLightRemoveButtonLocator = By.id("remove-sauce-labs-bike-light");
    private final By shoppingCartBadgeLocator = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");
    public void startPage() {
        openUrl("https://www.saucedemo.com/inventory.html");
    }

    public void addItemsToTheCart() {
        click(sauceLabsBackpackButtonLocator);
        click(sauceLabsBikeLightButtonLocator);
    }

    public boolean areButtonsChangeDisplayedNameToRemove() {
        try {
            return find(sauceLabsBackpackRemoveButtonLocator).isDisplayed() && find(sauceLabsBikeLightRemoveButtonLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean shoppingCartBadgeCountEqualsTo(int val) {
        try {
            return Objects.equals(find(shoppingCartBadgeLocator).getText(), String.valueOf(val));
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isSauceLabsBackpackButtonDisplayed() {
        try {
            return find(sauceLabsBackpackButtonLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
