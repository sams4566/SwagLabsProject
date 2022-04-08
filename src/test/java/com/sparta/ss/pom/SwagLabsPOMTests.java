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
    private static String standardUserName;
    private static String userName = "standard_user";

    @BeforeAll
    static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        options = new ChromeOptions();
        options.addArguments("headless");
        service = new ChromeDriverService.Builder().usingDriverExecutable(new File("src/test/resources/chromedriver")).usingAnyFreePort().build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        login = new SLLogin(driver);

        standardUserName = "standard_user";

        products = new SLProducts(driver);
    }

    @Nested
    class ProductPage {
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

    }

    @Nested
    @DisplayName("Cart Tests")
    class CartTests {
        @Test
        @DisplayName("Check Url is correct for cart")
        void checkUrlIsCorrectForCart() {
            assertEquals(login.goToCartPage(standardUserName).getUrl(), "https://www.saucedemo.com/cart.html");
        }

        @Test
        @DisplayName("Check backpack is in the cart")
        void checkBackpackIsInTheCart() {
            Assertions.assertTrue(login.goToCartPage(standardUserName).retrieveBackpackInCart());
        }

        @Test
        @DisplayName("Check backpack has been removed from cart")
        void checkBackpackHasBeenRemovedFromCart() {
            Assertions.assertTrue(login.goToCartPage(standardUserName).removeBackpackInCart());
        }
    }

    @Nested
    @DisplayName("Checkout page")
    class CheckoutTests{

        @Test
        @DisplayName("Check url is correct ")
        void checkUrlIsCorrect() {
            assertEquals("https://www.saucedemo.com/checkout-step-one.html", login.goToCheckoutPage(standardUserName).getUrl());
        }

        @Test
        @DisplayName("check continue button takes you to correct url")
        void checkContinueButtonTakesYouToCorrectUrl() {
            assertEquals(login.goToCheckoutPage(standardUserName).inputInformation().getUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        }

        @Test
        @DisplayName("check finish button takes you to correct url")
        void checkFinishButtonTakesYouToCorrectUrl() {
            assertEquals(login.goToCheckoutPage(standardUserName).checkoutStepTwo().getUrl(), "https://www.saucedemo.com/checkout-complete.html");
        }

        @Test
        @DisplayName("Check back home button returns to products")
        void checkBackToHomeButtonReturnsToProducts() {
            Assertions.assertTrue(login.goToCheckoutPage(standardUserName).doesBackHomeReturnsToHome());
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
