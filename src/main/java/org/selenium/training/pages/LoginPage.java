package org.selenium.training.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameLocator = By.xpath("//input[@id='user-name']");
    private final By passwordLocator = By.xpath("//input[@id='password']");
    private final By buttonLocator = By.xpath("//input[@type='submit']");
    private final By errorLocator = By.className("error-button");

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void startPage() {
        openUrl("https://www.saucedemo.com/");
    }

    public void Login(String username, String password) {
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(buttonLocator);
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return find(errorLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
