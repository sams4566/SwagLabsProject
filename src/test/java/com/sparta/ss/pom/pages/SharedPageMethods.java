package com.sparta.ss.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class SharedPageMethods {

    public String getUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

}
