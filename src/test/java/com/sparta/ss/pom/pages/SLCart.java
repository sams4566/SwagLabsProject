package com.sparta.ss.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SLCart {
    private WebDriver driver;
    By cart = new By.ByClassName("shopping_cart_link");

    public SLCart(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        driver.findElement(cart).click();
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
        String cartItem = driver.findElement(By.className("inventory_item_name")).getText();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        return !cartItem.equals("Sauce Labs Backpack");
    }


}
