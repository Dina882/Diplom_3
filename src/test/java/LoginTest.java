import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.example.PasswordPage;
import org.example.LoginPage;
import org.example.MainPage;
import org.example.RegisterPage;
import org.example.User;
import org.example.UserClient;
import org.example.UserGenerator;

public class LoginTest extends BaseTest {
    private User user;
    private final UserGenerator userGenerator = new UserGenerator();
    private final UserClient userClient = new UserClient();
    private String accessToken;
    //Регистрация аккаунта
    @Before
    public void accountTestRegistration() {
        user = userGenerator.random();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
    }
    //вход по кнопке «Войти в аккаунт» на главной
    @Test
    public void loginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLogInButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        boolean expected = true;
        boolean actual = mainPage.checkoutButtonIsDisplayed();
        Assert.assertEquals("Пользовтель не смог авторизоваться", expected, actual);
    }
    // вход через кнопку «Личный кабинет»
    @Test
    public void loginFromPersonalArea() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAreaButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        boolean expected = true;
        boolean actual = mainPage.checkoutButtonIsDisplayed();
        Assert.assertEquals("Пользовтель не смог авторизоваться", expected, actual);
    }
    //вход через кнопку в форме регистрации
    @Test
    public void loginFromRegistrationPage() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.clickLogInLink();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        boolean expected = true;
        boolean actual = mainPage.checkoutButtonIsDisplayed();
        Assert.assertEquals("Данные не совпадают", expected, actual);
    }
    //вход через кнопку в форме восстановления пароля.
    @Test
    public void loginFromForgotPwdPage() {
        PasswordPage forgotPwdPage = new PasswordPage(driver);
        forgotPwdPage.open();
        forgotPwdPage.clickLogInLink();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        boolean expected = true;
        boolean actual = mainPage.checkoutButtonIsDisplayed();
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

