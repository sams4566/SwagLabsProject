package com.sparta.ss.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SLLogin {
    private WebDriver driver;
    By username = new By.ById("user-name");
    By password = new By.ById("password");


    public SLLogin(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.saucedemo.com/");
    }

    public SLProducts goToProductsPage(String userName) {
        driver.findElement(username).sendKeys(userName, Keys.TAB);
        driver.findElement(password).sendKeys("secret_sauce", Keys.ENTER);
        return new SLProducts(driver);
    }
}
