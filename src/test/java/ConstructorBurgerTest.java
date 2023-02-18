import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.MainPage;
import java.time.Duration;
import static org.hamcrest.Matchers.containsString;

public class ConstructorBurgerTest extends BaseTest {
    private MainPage mainPage;

    @Before
    public void openMainPage() {
        mainPage = new MainPage(driver);
        mainPage.open();
    }
    @Test
    public void selectBuns() {
        mainPage.clickSauceButton();
        mainPage.clickBunsButton();
        boolean expected = true;
        boolean actual = mainPage.checkoutSubtitleBunIsDisplayed();
        Assert.assertEquals("Данные не совпадают", expected, actual);
    }
    @Test
    public void selectSauces() {
        mainPage.clickSauceButton();
        MatcherAssert.assertThat(mainPage.getSaucesButtonParentClass(), containsString("current"));
        boolean expected = true;
        boolean actual = mainPage.checkoutSubtitleSaucesIsDisplayed();
        Assert.assertEquals("Данные не совпадают", expected, actual);
    }
    @Test
    public void selectFillings() {
        mainPage.clickFillingsButton();
        boolean expected = true;
        boolean actual = mainPage.checkoutSubtitleFillingIsDisplayed();
        Assert.assertEquals("Данные не совпадают", expected, actual);
    }
    @Test
    public void scrollBuns() {
        mainPage.clickFillingsButton();
        mainPage.scrollToBunsSubtitle();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(mainPage.getFillingsButton()));
        MatcherAssert.assertThat(mainPage.getBunsButtonParentClass(), containsString("current"));
    }
    @Test
    public void scrollSauces() {
        mainPage.scrollToSaucesSubtitle();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(mainPage.getBunsButton()));
        MatcherAssert.assertThat(mainPage.getSaucesButtonParentClass(), containsString("current"));
    }
    @Test
    public void scrollFillings() {
        mainPage.scrollToFillingsSubtitle();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(mainPage.getBunsButton()));
        MatcherAssert.assertThat(mainPage.getFillingsButtonParentClass(), containsString("current"));
    }
}

