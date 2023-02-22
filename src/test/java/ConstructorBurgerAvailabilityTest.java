import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.pom.ProfilePage;
import org.example.pom.LoginPage;
import org.example.pom.MainPage;
import org.example.pom.RegisterPage;
import org.example.user.User;
import org.example.user.UserClient;
import org.example.user.UserGenerator;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class ConstructorBurgerAvailabilityTest extends BaseTest {
    private User user;
    private final UserGenerator userGenerator = new UserGenerator();
    private final UserClient userClient = new UserClient();
    private String accessToken;

    //Регистрация тестового аккаунта
    @Before
    @DisplayName("Регистрация тестового аккаунта")
    public void accountRegistration() {
        user = userGenerator.random();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        String expected = "Вход";
        String actual = loginPage.getTitleTextInput();
        Assert.assertEquals("Не совершен выход из аккаунта пользователя", expected, actual);
        loginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAreaButton();
    }
    //Переход из личного кабинета в «Конструктор».
    @Test
    @DisplayName("Переход из личного кабинета в «Конструктор».")
    public void constructorButtonFromPersonalAccount() {
        ProfilePage accountProfilePage = new ProfilePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(accountProfilePage.getProfileSection()));
        accountProfilePage.clickBurgerConstructorButton();
        MainPage mainPage = new MainPage(driver);
        String expected = "Соберите бургер";
        String actual = mainPage.getTitleTextAssembleBurger();
        Assert.assertEquals("Данные не совпадают", expected, actual);
    }
    //Переход из личного кабинета в Лого
    @Test
    @DisplayName("Переход из личного кабинета в Лого")
    public void logoButtonFromPersonalAccount() {
        ProfilePage accountProfilePage = new ProfilePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(accountProfilePage.getProfileSection()));
        accountProfilePage.clickLogoButton();
        MainPage mainPage = new MainPage(driver);
        assertTrue("Данные не совпадают", mainPage.checkoutButtonIsDisplayed());
    }
    @After
    public void accountDelete() {
        accessToken = userClient.getAccessTokenOnLogin(user);
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }
}

