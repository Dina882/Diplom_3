package org.example;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;
    private final static String URL_REGISTER = "https://stellarburgers.nomoreparties.site/register";
    private final By logInLink = By.xpath(".//a[text()='Войти']");
    private final By nameInput = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By errorIncorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open() {
        driver.get(URL_REGISTER);
    }
    public void inputName(String text) {
        driver.findElement(nameInput).sendKeys(text);
    }
    public void inputEmail(String text) {
        driver.findElement(emailInput).sendKeys(text);
    }
    public void inputPassword(String text) {
        driver.findElement(passwordInput).sendKeys(text);
    }
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }
    public void clickLogInLink() {
        driver.findElement(logInLink).click();
    }
    @Step("Получить текст ошибки некорректного пароля")
    public String getErrorPasswordText(){return driver.findElement(errorIncorrectPassword).getText();}

    @Step
    public void register(String name, String email, String password) {
        inputName(name);
        inputEmail(email);
        inputPassword(password);
        clickRegistrationButton();
    }
}
