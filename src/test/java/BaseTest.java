import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        /*Yandex browser:
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriverya106.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\svetl\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
         */

        //Chrome browser:
        System.setProperty("webdriver.chrome.driver", "C:/Program Files/WebDriver/bin/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}