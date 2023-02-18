import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.example.ProfilePage;
import org.example.LoginPage;
import org.example.MainPage;
import org.example.RegisterPage;
import org.example.User;
import org.example.UserClient;
import org.example.UserGenerator;

public class PersonalAccountTest extends BaseTest {
    private User user;
    private final UserGenerator userGenerator = new UserGenerator();
    private final UserClient userClient = new UserClient();
    private String accessToken;

    @Before
    public void accountRegistration() {
        user = userGenerator.random();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
    }
    //переход по клику на «Личный кабинет». Пользователь не залогинен.
    @Test
    public void personalAreaNotLoggedInFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalAreaButton();
        LoginPage loginPage = new LoginPage(driver);
        String expected = "Вход";
        String actual = loginPage.getTitleTextInput();
        Assert.assertEquals("Не совершен выход из аккаунта пользователя", expected, actual);
    }
    //переход по клику на «Личный кабинет». Пользователь залогинен.
    @Test
    public void personalAreaLoggedInFromMainPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAreaButton();
        ProfilePage profilePage = new ProfilePage(driver);
        boolean expected = true;
        boolean actual = profilePage.checkProfileButton();
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
