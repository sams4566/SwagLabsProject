package com.sparta.ss.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SLCart {
    private WebDriver driver;
    By cart = new By.ByClassName("shopping_cart_link");
    By checkout = new By.ById("checkout");

    public SLCart(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        driver.findElement(cart).click();
//        driver.findElement(checkout).click();
        return driver.getCurrentUrl();
    }

    public boolean retrieveBackpackInCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(cart).click();
        String cartItem = driver.findElement(By.className("inventory_item_name")).getText();
        return cartItem.equals("Sauce Labs Backpack");
    }

    public boolean removeBackpackInCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(cart).click();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        return driver.findElements(By.className("cart_item")).size() == 0;
    }

    public boolean getNumberOfItemsInCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(cart).click();
        int numberOfItems = driver.findElements(By.className("cart_item")).size();
        return numberOfItems == 2;
    }

    public boolean getNumberInCartImage() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(cart).click();
        String numberOfItems = driver.findElement(By.className("shopping_cart_badge")).getText();
        return numberOfItems.equals("2");
    }
}
