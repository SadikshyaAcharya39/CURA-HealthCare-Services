package pageobject;

import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    // Locators
    public By homePageHeader = By.xpath("//h1[normalize-space()='CURA Healthcare Service']");
    public By userNameField = By.xpath("//input[@id='txt-username']");
    public By passwordField = By.xpath("//input[@id='txt-password']");
    public By hamburgerButton = By.xpath("//a[@id='menu-toggle']");
    public By loginText = By.xpath("//a[normalize-space()='Login']");
    public By loginButton = By.xpath("//button[@id='btn-login']");
    public By errorMessage = By.xpath("//p[@class='lead text-danger']");
    public By sideBar = By.xpath("//nav[@id='sidebar-wrapper']");
    public By homeText = By.xpath("//a[normalize-space()='Home']");
    public By historyText = By.cssSelector("a[href='history.php#history']");
    public By logoutText = By.xpath("//a[@href='authenticate.php?logout']");
    public By profileText = By.xpath("//a[normalize-space()='Profile']");
    public By appointmentText = By.xpath("//h2[normalize-space()='Make Appointment']");



    // Methods
    public void loginToApp(String username, String password){
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }


}
