package org.example;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final static String URL_REGISTER = "https://stellarburgers.nomoreparties.site/login";
    private final By headingInput = By.xpath(".//*[@id='root']//h2[.='Вход']");
    private final By enterSection = By.xpath(".//h2[text()='Вход']");
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By logInButton = By.xpath(".//button[text()='Войти']");
    private final By forgotPwdLink = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Получить текст заголовка 'Вход'")
    public String getTitleTextInput(){
        return driver.findElement(headingInput).getText();
    }

    public void inputEmail(String text) {driver.findElement(emailInput).sendKeys(text);}
    public void inputPassword(String text) {driver.findElement(passwordInput).sendKeys(text);}
    public void clickLoginButton() {driver.findElement(logInButton).click();}
    public void open() {driver.get(URL_REGISTER);}
    @Step
    public void login(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        new WebDriverWait(driver, Duration.ofSeconds(10));
        clickLoginButton();
    }
}
