package org.example;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;
    private By profileButton = By.xpath(".//a[text()='Профиль']");
    private final By burgerConstructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logoButton = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    private final By logOutButton = By.xpath(".//button[text()='Выход']");
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    public By getProfileSection() {
        return profileButton;
    }

    @Step("Наличие кнопки 'Профиль'")
    public boolean checkProfileButton(){return driver.findElement(profileButton).isDisplayed();}
    public void clickBurgerConstructorButton() {
        driver.findElement(burgerConstructorButton).click();
    }
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }
    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }
}