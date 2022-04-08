package com.sparta.ss.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SLLogin {
    private WebDriver driver;
    By username = new By.ById("user-name");
    By password = new By.ById("password");
    By shoppingCart = new By.ByClassName("shopping_cart_link");
    By checkoutButton = new By.ById("checkout");

    public SLLogin(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.saucedemo.com/");
    }

    public SLProducts goToProductsPage(String userName) {
        driver.findElement(username).sendKeys(userName, Keys.TAB);
        driver.findElement(password).sendKeys("secret_sauce", Keys.ENTER);
        return new SLProducts(driver);
    }

    public SLCheckout goToCheckoutPage(String userName) {
        driver.findElement(username).sendKeys(userName, Keys.TAB);
        driver.findElement(password).sendKeys("secret_sauce", Keys.ENTER);
        driver.findElement(shoppingCart).click();
        driver.findElement(checkoutButton);
        return new SLCheckout(driver);
    }

    public SLCart goToCartPage(String userName) {
        driver.findElement(username).sendKeys(userName, Keys.TAB);
        driver.findElement(password).sendKeys("secret_sauce", Keys.ENTER);
        driver.findElement(password).click();
        return new SLCart(driver);
    }
}
