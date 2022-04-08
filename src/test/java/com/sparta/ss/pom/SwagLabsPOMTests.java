package com.sparta.ss.pom;

import com.sparta.ss.pom.pages.SLLogin;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwagLabsPOMTests {
    private static WebDriver driver;
    private SLLogin login;
    private static ChromeOptions options;
    private static ChromeDriverService service;
    private static String userName;

    @BeforeAll
    static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        options = new ChromeOptions();
        options.addArguments("headless");
        service = new ChromeDriverService.Builder().usingDriverExecutable(new File("src/test/resources/chromedriver.exe")).usingAnyFreePort().build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver(service, options);
        login = new SLLogin(driver);
        userName = "standard_user";
    }

    @Nested
    @DisplayName("Cart Tests")
    class CartTests {
        @Test
        @DisplayName("Check Url is correct for cart")
        void checkUrlIsCorrectForCart() {
            assertEquals(login.goToCartPage(userName).getUrl(), "https://www.saucedemo.com/cart.html");
        }

        @Test
        @DisplayName("Check backpack is in the cart")
        void checkBackpackIsInTheCart() {
            Assertions.assertTrue(login.goToCartPage(userName).retrieveBackpackInCart());
        }

        @Test
        @DisplayName("Check backpack has been removed from cart")
        void checkBackpackHasBeenRemovedFromCart() {
            Assertions.assertTrue(login.goToCartPage(userName).removeBackpackInCart());
        }
    }

    @AfterEach
    void teardown() {
        driver.close();
    }

    @AfterAll
    static void teardownAll() {
        driver.quit();
        service.stop();
    }
}
