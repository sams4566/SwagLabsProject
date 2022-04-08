package com.sparta.ss.pom;

import com.sparta.ss.pom.pages.SLLogin;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class SwagLabsPOMTests {
    private static WebDriver driver;
    private SLLogin login;
    private static ChromeOptions options;
    private static ChromeDriverService service;

    @BeforeAll
    static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    }

    @BeforeEach
    void setup() {
        options = new ChromeOptions();
        options.addArguments("headless");
        service = new ChromeDriverService.Builder().usingDriverExecutable(new File("src/test/resources/chromedriver.exe")).usingAnyFreePort().build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = new ChromeDriver(service, options);
        login = new SLLogin(driver);
    }

}
