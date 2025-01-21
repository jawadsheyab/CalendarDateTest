import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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
    public void testDatePicker() {
        int day = 1;
        int month = 1;
        int year = 1995;
        calenderPage.pickDate(day, month, year);
        System.out.println(calenderPage.getDateValue());
        assertEquals(calenderPage.getDateValue(), String.format("%02d/%02d/%04d", month, day, year));
    }
}
