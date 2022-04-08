package com.sparta.ss.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SLCheckout extends SharedPageMethods {
    private WebDriver driver;
    By firstName = new By.ById("first-name");
    By lastName = new By.ById("last-name");
    By postCode = new By.ById("postal-code");
    By continueButton = new By.ById("continue");
    By errorButton = new By.ByClassName("error-button");
    By finishButton = new By.ById("finish");
    By backHomeButton = new By.ById("back-to-products");


    public SLCheckout(WebDriver driver) {
        this.driver = driver;
    }

    public SLCheckout inputInformation(){
        driver.findElement(firstName).sendKeys("First", Keys.TAB);
        driver.findElement(lastName).sendKeys("Last", Keys.TAB);
        driver.findElement(postCode).sendKeys("postcode", Keys.ENTER);
        return new SLCheckout(driver);
    }

    public boolean noInfoInputted(){
        driver.findElement(continueButton).click();
        return driver.findElement(errorButton).isDisplayed();
    }

    public SLCheckout checkoutStepTwo(){
        inputInformation().driver.findElement(finishButton).click();
        return new SLCheckout(driver);
    }

    public boolean backHomeReturnsToHome(){
        inputInformation().checkoutStepTwo().driver.findElement(backHomeButton).click();
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }




}
