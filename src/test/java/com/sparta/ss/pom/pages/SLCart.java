package com.sparta.ss.pom.pages;

import org.openqa.selenium.WebDriver;

public class SLCart {
    private WebDriver driver;

    public SLCart(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}
