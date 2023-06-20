package org.selenium.training.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {
    public CheckoutStepTwoPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private final By finishButtonLocator = By.id("finish");
    private final By cancelButtonLocator = By.id("cancel");
    private final By backpackItemLocator = By.xpath("//*[@id=\"item_4_title_link\"]/div");
    private final By bikeLightItemLocator = By.xpath("//*[@id=\"item_0_title_link\"]/div");
    private final By summaryInfoLocator = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]");

    public void startPage() {
        openUrl("https://www.saucedemo.com/checkout-step-two.html");
    }
    public boolean checkIfItemsInTheInventoryList() {
        try {
            return find(backpackItemLocator).getText().equals("Sauce Labs Backpack") &&
                    find(bikeLightItemLocator).getText().equals("Sauce Labs Bike Light");
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean checkTotalIsEqualTo(double total) {
        try {
            String sum = find(summaryInfoLocator).getText().split("\\$")[1];
            return sum.equals(String.valueOf(total));
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickCancelButton() {
        click(cancelButtonLocator);
    }

    public void clickFinishButton() {
        click(finishButtonLocator);
    }

}
