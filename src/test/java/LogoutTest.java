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

public class LogoutTest extends BaseTest {
    private User user;
    private final UserGenerator userGenerator = new UserGenerator();
    private final UserClient userClient = new UserClient();
    private String accessToken;

    //Успешная регистрацию.
    @Before
    @DisplayName("Успешная регистрацию.")
    public void accountRegistration() {
        user = userGenerator.random();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        String expected = "Вход";
        String actual = loginPage.getTitleTextInput();
        Assert.assertEquals("Данные не совпадают", expected, actual);
        loginPage.login(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAreaButton();
    }
    //выход по кнопке «Выйти» в личном кабинете.
    @Test
    @DisplayName("Dыход по кнопке «Выйти» в личном кабинете.")
    public void constructorButtonFromPersonalArea() {
        ProfilePage accountProfilePage = new ProfilePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(accountProfilePage.getProfileSection()));
        accountProfilePage.clickLogOutButton();
        LoginPage loginPage = new LoginPage(driver);
        String expected = "Вход";
        String actual = loginPage.getTitleTextInput();
        Assert.assertEquals("Данные не совпадают", expected, actual);
    }
    @After
    public void accountDelete() {
        accessToken = userClient.getAccessTokenOnLogin(user);
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }
}
