import com.sparta.ss.pom.pages.SLCart;
import com.sparta.ss.pom.pages.SLLogin;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class MyStepdefs {
    private static WebDriver driver;
    private SLLogin login;
    private static ChromeOptions options;
    private static ChromeDriverService service;
    private static String userName;
    private SLCart cartPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
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
        userName = "standard_user";
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        login = new SLLogin(driver);
    }

    @When("I click on the cart link")
    public void iClickOnTheCartLink() {
        cartPage = login.goToCartPage(userName);
    }

    @Then("I receive the correct link")
    public void iReceiveTheCorrectLink() {
        Assertions.assertEquals(cartPage.getUrl() ,"https://www.saucedemo.com/cart.html");
    }
}
