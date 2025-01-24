
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.LoginPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private Properties properties;

    @BeforeEach
    public void setUp(){
        driver = new FirefoxDriver();
        properties = new Properties();
        try (InputStream is = getClass().getResourceAsStream("application.properties")) {
            try {
                properties.load(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.get(properties.getProperty("baseURL"));
        driver.manage().window().maximize();

        // Send driver to the login test class
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }

    @Test
    public void loginPageFunctionality(){
        WebElement header = driver.findElement(loginPage.homePageHeader);
        String actual = header.getText();
        Assertions.assertEquals("CURA Healthcare Service", actual, "Matched");
    }

    @Test
    public void IncorrectUsernameIncorrectPassword(){
        driver.get(properties.getProperty("loginURL"));
        loginPage.loginToApp("incorrectUsername", "incorrectPassword");
        WebElement errorMessage = driver.findElement(loginPage.errorMessage);
        String actualMessage = errorMessage.getText();
        Assertions.assertEquals("Login failed! Please ensure the username and password are valid.", actualMessage, "Does not match");
    }

    @Test
    public void IncorrectUsernameCorrectPassword(){
        driver.get("loginURL");
        loginPage.loginToApp("incorrectUsername", "correctPassword");
        WebElement errorMessage = driver.findElement(loginPage.errorMessage);
        String actualMessage = errorMessage.getText();
        Assertions.assertEquals("Login failed! Please ensure the username and password are valid.", actualMessage, "Does not match");
    }

    @Test
    public void CorrectUsernameIncorrectPassword(){
        driver.get("loginURL");
        loginPage.loginToApp("correctUsername", "incorrectPassword");
        WebElement errorMessage = driver.findElement(loginPage.errorMessage);
        String actualMessage = errorMessage.getText();
        Assertions.assertEquals("Login failed! Please ensure the username and password are valid.", actualMessage, "Does not match");
    }

    @Test
    public void CorrectUsernameCorrectPasswordAllCaps(){
        driver.get("loginURL");
        loginPage.loginToApp("userNameAllCaps", "passwordAllCaps");
        WebElement errorMessage = driver.findElement(loginPage.errorMessage);
        String actualMessage = errorMessage.getText();
        Assertions.assertEquals("Login failed! Please ensure the username and password are valid.", actualMessage, "Does not match");
    }

    @Test
    public void CorrectUsernameCorrectPasswordAllInLowercase(){
        driver.get("loginURL");
        loginPage.loginToApp("userNameAllSmall", "passwordAllSmall");
        WebElement errorMessage = driver.findElement(loginPage.errorMessage);
        String actualMessage = errorMessage.getText();
        Assertions.assertEquals("Login failed! Please ensure the username and password are valid.", actualMessage, "Does not match");
    }

    @Test
    public void CorrectUsernameCorrectPassword(){
        driver.get("loginURL");
        loginPage.loginToApp("correctUsername", "correctPassword");
        String expectedURL = "appointmentPageURL";
        String actualURL = driver.getCurrentUrl();
        Assertions.assertEquals(expectedURL, actualURL, "Does not navigated to the Home Page");

        WebElement header = driver.findElement(loginPage.appointmentText);
        Assertions.assertEquals("Make Appointment", header.getText(), "Title does not Match");
    }


    //  After Login

    @Test
    public void navigatingtoHomePage(){
//        loginTest.CorrectUsernameCorrectPassword();

        driver.get("loginURL");
        loginPage.loginToApp("correctUsername", "correctPassword");

        String expectedURL = "appointmentPageURL";
        String actualURL = driver.getCurrentUrl();
        Assertions.assertEquals(expectedURL, actualURL, "Navigated to the Home Page");
        driver.findElement(loginPage.hamburgerButton).click();
        driver.findElement(loginPage.homeText).click();
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals("baseURL", currentURL, "URL doesnot match");
    }

    @Test
    public void navigatingtoHistoryPage(){
//        loginTest.CorrectUsernameCorrectPassword();

        driver.get("loginURL");
        loginPage.loginToApp("correctUsername", "correctPassword");
        String expectedURL = "appointmentPageURL";
        String actualURL = driver.getCurrentUrl();
        Assertions.assertEquals(expectedURL, actualURL, "Navigated to the Home Page");

        driver.findElement(loginPage.hamburgerButton).click();
        driver.findElement(loginPage.historyText).click();
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals("historyPageURL", currentURL, "URL doesnot match");
    }

    @Test
    public void navigatingToProfilePage(){
//        loginTest.CorrectUsernameCorrectPassword();

        driver.get("loginURL");
        loginPage.loginToApp("correctUsername", "correctPassword");

        String expectedURL = "appointmentPageURL";
        String actualURL = driver.getCurrentUrl();
        Assertions.assertEquals(expectedURL, actualURL, "Navigated to the Home Page");
        driver.findElement(loginPage.hamburgerButton).click();
        driver.findElement(loginPage.profileText).click();
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals("profileURL", currentURL, "URL does not match");
    }

    @Test
    public void navigatingToLogoutPage(){
//        loginTest.CorrectUsernameCorrectPassword();
        driver.get("loginURL");
        loginPage.loginToApp("correctUsername", "correctPassword");
        String expectedURL = "appointmentPageURL";
        String actualURL = driver.getCurrentUrl();
        Assertions.assertEquals(expectedURL, actualURL, "Navigated to the Home Page");
        driver.findElement(loginPage.hamburgerButton).click();
        driver.findElement(loginPage.logoutText).click();
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals("baseURL", currentURL, "URL does not match");
    }

}
