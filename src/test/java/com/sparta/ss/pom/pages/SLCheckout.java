package com.sparta.ss.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SLCheckout {
    private WebDriver driver;
    By firstName = new By.ById("first-name");
    By lastName = new By.ById("last-name");
    By postCode = new By.ById("postal-code");
    By continueButton = new By.ById("continue");
//    By errorButton = new By.ByClassName("error-button");
    By finishButton = new By.ById("finish");
    By backHomeButton = new By.ById("back-to-products");
//    By errorContainer = new By.ByClassName("error-message-container error");
//    By shoppingCart = new By.ByClassName("shopping_cart_link");
//    By checkoutButton = new By.ById("checkout");


    public SLCheckout(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }


    public SLCheckout inputInformation(){
        driver.findElement(firstName).sendKeys("First", Keys.TAB);
        driver.findElement(lastName).sendKeys("Last", Keys.TAB);
        driver.findElement(postCode).sendKeys("Postcode");
        driver.findElement(continueButton).click();
        return new SLCheckout(driver);
    }

    public SLCheckout checkoutStepTwo(){
        inputInformation().driver.findElement(finishButton).click();
        return new SLCheckout(driver);
    }

//    public boolean doesErrorMessageAskForFirstName(){
//        driver.findElement(shoppingCart).click();
//        driver.findElement(checkoutButton).click();
//        driver.findElement(lastName).sendKeys("Last", Keys.TAB);
//        driver.findElement(postCode).sendKeys("Postcode", Keys.ENTER);
//        return driver.findElement(errorContainer).getText().equals("Error: First Name is required");
//    }
//
//    public boolean doesErrorMessageAskForLastName(){
//        driver.findElement(shoppingCart).click();
//        driver.findElement(checkoutButton).click();
//        driver.findElement(firstName).sendKeys("First", Keys.TAB);
//        driver.findElement(postCode).sendKeys("Postcode", Keys.ENTER);
//        return driver.findElement(errorContainer).getText().equals("Error: Last Name is required");
//    }
//
//    public boolean doesErrorMessageAskForPostCode(){
//        driver.findElement(shoppingCart).click();
//        driver.findElement(checkoutButton).click();
//        driver.findElement(firstName).sendKeys("First", Keys.TAB);
//        driver.findElement(lastName).sendKeys("Last", Keys.ENTER);
//        return driver.findElement(errorContainer).getText().equals("Error: Postal Code is required");
//    }
//
//    public boolean doesErrorShowWhenNothingIsInputted(){
//        driver.findElement(shoppingCart).click();
//        driver.findElement(checkoutButton).click();
//        driver.findElement(continueButton).click();
//        return driver.findElement(errorButton).isDisplayed();
//    }
//
//    public boolean doesErrorButtonDisappearAfterClick(){
//        driver.findElement(shoppingCart).click();
//        driver.findElement(checkoutButton).click();
//        driver.findElement(continueButton).click();
//        driver.findElement(errorButton).click();
//        return !driver.findElement(errorButton).isDisplayed();
//    }

    public boolean doesBackHomeReturnsToHome(){
        checkoutStepTwo().driver.findElement(backHomeButton).click();
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }



}
