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
    private static String standardUserName;

    @BeforeAll
    static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        options = new ChromeOptions();
        service = new ChromeDriverService.Builder().usingDriverExecutable(new File("src/test/resources/chromedriver.exe")).usingAnyFreePort().build();
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

//        @Test
//        @DisplayName("check error is thrown when no data is inputted")
//        void checkErrorIsThrownWhenNoDataIsInputted() {
//            Assertions.assertTrue(login.goToCheckoutPage(standardUserName).doesErrorShowWhenNothingIsInputted());
//        }
//
//        @Test
//        @DisplayName("check error is thrown for firs name when other fields are poplated")
//        void checkErrorIsThrownForFirstNameWhenOtherFieldsArePoplated() {
//            Assertions.assertTrue(login.goToCheckoutPage(standardUserName).doesErrorMessageAskForFirstName());
//        }
//
//        @Test
//        @DisplayName("check error is thrown for last name when other fields are poplated")
//        void checkErrorIsThrownForLastNameWhenOtherFieldsArePoplated() {
//            Assertions.assertTrue(login.goToCheckoutPage(standardUserName).doesErrorMessageAskForLastName());
//        }
//
//        @Test
//        @DisplayName("check error is thrown for postcode when other fields are poplated")
//        void checkErrorIsThrownForPostcodeWhenOtherFieldsArePoplated() {
//            Assertions.assertTrue(login.goToCheckoutPage(standardUserName).doesErrorMessageAskForPostCode());
//        }

//        @Test
//        @DisplayName("Check error message disappears when button is pressed")
//        void checkErrorMessageDisappearsWhenButtonIsPressed() {
//            Assertions.assertTrue(login.goToCheckoutPage(standardUserName).doesErrorButtonDisappearAfterClick());
//        }
//
//        @Test
//        @DisplayName("Check back home button returns to products")
//        void checkBackToHomeButtonReturnsToProducts() {
//            Assertions.assertTrue(login.goToCheckoutPage(standardUserName).doesBackHomeReturnsToHome());
//        }



        
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
