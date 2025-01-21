import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalenderTest {

    private WebDriver driver;
    private CalenderPage calenderPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.bestcase.com/date-calculator/");
        driver.manage().window().maximize();
        calenderPage = new CalenderPage(driver);
    }

    @Test
    public void testPickUpYear() {
        calenderPage.selectYear(1994);
    }
}
