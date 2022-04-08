package com.sparta.ss.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SLProducts {
    private WebDriver driver;

    public SLProducts(WebDriver driver) {
        this.driver = driver;

    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }


    public boolean isNumberOfProductsDisplayedSix() {
        int numberOfResults = driver.findElement(By.className("inventory_list")).findElements(By.className("inventory_item")).size();
        return numberOfResults == 6;
    }

    public boolean isThereImageOfAllProducts() {
        List<WebElement> elements = driver.findElement(By.className("inventory_list")).findElements(By.className("inventory_item"));
        for (WebElement element : elements) {
            if (!element.findElement(By.className("inventory_item_img")).findElement(By.className("inventory_item_img")).getAttribute("src").contains("jpg")) {
                return false;
            }
        }
        return true;
    }

    public boolean isTitleProducts() {
        return driver.findElement(By.className("title")).getText().contains("PRODUCT");
    }


    public boolean checkIfAddedItemShownAsCartNumber() {
        return true;
    }

    public boolean isAddToCartButtonAvailableForAllProducts() {
        List<WebElement> elements = driver.findElement(By.className("inventory_list")).findElements(By.className("inventory_item"));
        for (WebElement element : elements) {
            if (!element.findElement(By.className("pricebar")).getText().contains("ADD TO CART")) {
                return false;
            }
        }
        return true;
    }


}
