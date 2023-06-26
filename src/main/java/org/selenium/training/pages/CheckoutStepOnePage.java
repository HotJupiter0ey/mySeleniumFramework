package org.selenium.training.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage{
    public CheckoutStepOnePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private final By firstNameFieldLocator = By.id("first-name");
    private final By lastNameFieldLocator = By.id("last-name");
    private final By postalCodeFieldLocator = By.id("postal-code");
    private final By continueButtonLocator = By.id("continue");
    private final By cancelButtonLocator = By.id("cancel");
    private final By error = By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3");

    public void startPage() {
        openUrl("https://www.saucedemo.com/checkout-step-one.html");
    }

    public void clickContinue(String firstname, String lastname, String postalCode) {
        type(firstname, firstNameFieldLocator);
        type(lastname, lastNameFieldLocator);
        type(postalCode, postalCodeFieldLocator);
        click(continueButtonLocator);
    }

    public void clickCancel() {
        click(cancelButtonLocator);
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return find(error).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
