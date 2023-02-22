package org.example.pom;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver;
    private final static String URL = "https://stellarburgers.nomoreparties.site/";
    private final By logInButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAreaButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By makeOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By bunsSubtitle = By.xpath(".//h2[text() ='Булки']");
    private final By sauсesSubtitle = By.xpath(".//h2[text() ='Соусы']");
    private final By fillingsSubtitle = By.xpath(".//h2[text() ='Начинки']");
    private final By fillingsButtonParent = By.xpath(".//span[text() ='Начинки']/parent::div");
    private final By bunsButton = By.xpath(".//span[text() ='Булки']");
    private final By bunsButtonParent = By.xpath(".//span[text() ='Булки']/parent::div");
    private final By sauсesButton = By.xpath(".//span[text() ='Соусы']");
    private final By sauсesButtonParent = By.xpath(".//span[text() ='Соусы']/parent::div");
    private final By headerAssembleBurger = By.xpath(".//h1[text()='Соберите бургер']");
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open() {
        driver.get(URL);
    }
    @Step
    public void scrollToBunsSubtitle() {((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(bunsSubtitle));}
    @Step
    public void scrollToSaucesSubtitle() {((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(sauсesSubtitle));}
    @Step
    public void scrollToFillingsSubtitle() {((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(fillingsSubtitle));}
    @Step("Наличие заголовка 'Булки'")
    public boolean checkoutSubtitleBunIsDisplayed(){
        return driver.findElement(bunsButton).isDisplayed();
    }
    @Step("Наличие заголовка 'Соусы'")
    public boolean checkoutSubtitleSaucesIsDisplayed(){
        return driver.findElement(sauсesSubtitle).isDisplayed();
    }
    @Step("Наличие заголовка 'Начинки'")
    public boolean checkoutSubtitleFillingIsDisplayed(){return driver.findElement(fillingsButtonParent).isDisplayed();}
    @Step("Наличие заголовка 'Оформить заказ'")
    public boolean checkoutButtonIsDisplayed(){
        return driver.findElement(makeOrderButton).isDisplayed();
    }
    @Step("Получить текста заголовка 'Соберите бургер'")
    public String getTitleTextAssembleBurger(){
        return driver.findElement(headerAssembleBurger).getText();
    }
    public void clickLogInButton() {
        driver.findElement(logInButton).click();
    }
    public void clickPersonalAreaButton() {
        driver.findElement(personalAreaButton).click();
    }
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }
    public void clickSauceButton() {
        driver.findElement(sauсesButton).click();
    }
    public void clickFillingsButton() {
        driver.findElement(fillingsSubtitle).click();
    }
    public WebElement getBunsButton() {
        return driver.findElement(bunsButton);
    }
    public WebElement getFillingsButton() {
        return driver.findElement(fillingsSubtitle);
    }
    public String getBunsButtonParentClass() {
        return driver.findElement(bunsButtonParent).getDomAttribute("class");
    }
    public String getSaucesButtonParentClass() {return driver.findElement(sauсesButtonParent).getDomAttribute("class");}
    public String getFillingsButtonParentClass() {return driver.findElement(fillingsButtonParent).getDomAttribute("class");}

}

