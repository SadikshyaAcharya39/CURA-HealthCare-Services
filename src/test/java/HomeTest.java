import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.HomePage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HomeTest {
    private WebDriver driver;
    private HomePage homePage;
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

        driver.get("https://katalon-demo-cura.herokuapp.com/");
        driver.manage().window().maximize();

        // Send a driver
        homePage = new HomePage(driver);

    }

    @AfterEach
    public void tearUp(){
        driver.quit();
    }

    @Test
    public void verifyingFacebookButton(){
        WebElement facebookIcon = driver.findElement(homePage.facebookIcon);
        facebookIcon.click();
        String currentURL =  driver.getCurrentUrl();
        Assertions.assertTrue(currentURL.contains("facebook"), "Does not navigate to the facebook page.");
    }

    @Test
    public void verifyingTwitterButton(){
        WebElement twitterIcon = driver.findElement(homePage.twitterIcon);
        twitterIcon.click();
        String currentURL =  driver.getCurrentUrl();
        Assertions.assertTrue(currentURL.contains("twitter"),"Does not navigate to the twitter page.");
    }

    @Test
    public void verifyingDribbleButton(){
        WebElement dribbleIcon = driver.findElement(homePage.dribbleIcon);
        dribbleIcon.click();
        String currentURL =  driver.getCurrentUrl();
        Assertions.assertTrue(currentURL.contains("dribble"), "Does not navigate to the dribble page.");
    }

    // Before Login

    // Hamburger Button Functionality

    @Test
    public void verifyingHamburgerButtonFunctionalityWithoutLogIn(){
        driver.findElement(homePage.hamburgerButton).click();
        WebElement sidebar = driver.findElement(homePage.sideBar);
        Assertions.assertTrue(sidebar.isDisplayed(), "Side bar is not displayed");
    }

    @Test
    public void navigatingtoHomePageUsingSideBar(){
        driver.findElement(By.xpath("//a[@id='menu-toggle']")).click();
        driver.findElement(homePage.homeText).click();
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals("baseURL", currentURL, "URL does not match");
    }

    @Test
    public void navigatingtoLoginPageUsingSideBar(){
        driver.findElement(homePage.hamburgerButton).click();
        driver.findElement(homePage.loginText).click();
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals("loginURL", currentURL, "URL does not match");
    }

}
