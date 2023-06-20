package org.selenium.training.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {
    public CheckoutCompletePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private final By backToProductsButtonLocator = By.id("back-to-products");
    private final By completeCheckoutImage = By.xpath("//*[@id=\"checkout_complete_container\"]/img");

    public void startPage() {
        openUrl("https://www.saucedemo.com/checkout-complete.html");
    }

    public boolean ifOrderCompleted() {
        try {
            return find(completeCheckoutImage).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickBackHomeButton() {
        click(backToProductsButtonLocator);
    }
}
