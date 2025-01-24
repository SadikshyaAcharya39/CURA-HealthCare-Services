package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    // Catch the driver
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    // Locators
    public By facebookIcon = By.xpath("//i[@class='fa fa-facebook fa-fw fa-3x']");
    public By twitterIcon = By.xpath("//i[@class='fa fa-twitter fa-fw fa-3x']");
    public By dribbleIcon = By.xpath("//i[@class='fa fa-dribbble fa-fw fa-3x']");
    public By hamburgerButton = By.xpath("//a[@id='menu-toggle']");
    public By loginText = By.xpath("//a[normalize-space()='Login']");
    public By sideBar = By.xpath("//nav[@id='sidebar-wrapper']");
    public By homeText = By.xpath("//a[normalize-space()='Home']");

    // Methods
}
