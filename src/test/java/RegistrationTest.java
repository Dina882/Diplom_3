import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.example.pom.LoginPage;
import org.example.pom.RegisterPage;
import org.example.user.User;
import org.example.user.UserClient;
import org.example.user.UserGenerator;

public class RegistrationTest extends BaseTest {
    private User user;
    private final UserGenerator userGenerator = new UserGenerator();
    private final UserClient userClient = new UserClient();
    private String accessToken;

    //Успешнаю регистрацию.
    @Test
    @DisplayName("Успешнаю регистрацию.")
    public void validRegistration() {
        user = userGenerator.random();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        LoginPage loginPage = new LoginPage(driver);
        String expected = "Вход";
        String actual = loginPage.getTitleTextInput();
        Assert.assertEquals("Данные не совпадают", expected, actual);
    }
    //Ошибка для некорректного пароля.
    @Test
    @DisplayName("Ошибка для некорректного пароля.")
    public void registrationWithIncorrectPwdErrHndl() {
        user = userGenerator.randomBadPwd();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        String expected = "Некорректный пароль";
        String actual = registerPage.getErrorPasswordText();
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
