package com.sparta.ss.pom;

import com.sparta.ss.pom.pages.SLLogin;
import com.sparta.ss.pom.pages.SLProducts;
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
    private SLProducts products;
    private static ChromeOptions options;
    private static ChromeDriverService service;
    private static String userName = "standard_user";


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
        products = new SLProducts(driver);
    }

    @Test
    @DisplayName("Check if product page is loaded after login")
    void checkIfProductPageIsLoadedAfterLogin() {
        Assertions.assertTrue(login.goToProductsPage(userName).getUrl().equals("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    @DisplayName("Check if the product page has six products displayed")
    void checkIfTheProductPageHasSixProductsDisplayed() {
        Assertions.assertTrue(login.goToProductsPage(userName).isNumberOfProductsDisplayedSix());
    }

    @Test
    @DisplayName("Check if there are images associated with all the products displayed")
    void checkIfThereAreImagesAssociatedWithAllTheProductsDisplayed() {
        Assertions.assertTrue(login.goToProductsPage(userName).isThereImageOfAllProducts());
    }

    @Test
    @DisplayName("Check if there is a product title")
    void checkIfThereIsAProductTitle() {
        Assertions.assertTrue(login.goToProductsPage(userName).isTitleProducts());
    }

    @Test
    @DisplayName("Check if there is add to cart button for each item")
    void checkIfThereIsAddToCartButtonForEachItem() {
        Assertions.assertTrue(login.goToProductsPage(userName).isAddToCartButtonAvailableForAllProducts());
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
