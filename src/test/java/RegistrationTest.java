import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.example.LoginPage;
import org.example.RegisterPage;
import org.example.User;
import org.example.UserClient;
import org.example.UserGenerator;

public class RegistrationTest extends BaseTest {
    private User user;
    private final UserGenerator userGenerator = new UserGenerator();
    private final UserClient userClient = new UserClient();
    private String accessToken;

    //Успешнаю регистрацию.
    @Test
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
