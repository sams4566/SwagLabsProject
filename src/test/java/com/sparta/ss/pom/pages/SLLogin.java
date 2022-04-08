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

    private void login(String userName) {
        driver.findElement(username).sendKeys(userName, Keys.TAB);
        driver.findElement(password).sendKeys("secret_sauce", Keys.ENTER);
    }

    public SLProducts goToProductsPage(String userName) {
        login(userName);
        return new SLProducts(driver);
    }


    public SLCheckout goToCheckoutPage(String userName) {
        login(userName);
        driver.findElement(shoppingCart).click();
        driver.findElement(checkoutButton).click();
        return new SLCheckout(driver);
    }

    public SLCart goToCartPage(String userName) {
        login(userName);
        return new SLCart(driver);
    }

}
