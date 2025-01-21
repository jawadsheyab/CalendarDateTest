import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CalenderPage {

    private WebDriver driver;
    private Actions action;
    private WebDriverWait wait;

    private By inputDateField = By.id("txtStartDate");
    private By prevButton = By.cssSelector("div[style=''] th[class='prev']");
    private By nextButton = By.cssSelector("div[style=''] th[class='next']");
    private By switchButton = By.cssSelector("div[style=''] th[class='datepicker-switch']");
    private By yearsList = By.xpath("//span[contains(@class, 'year')]");
    private String yearString = "//span[contains(@class, 'year') and (text()='%d')]";
    private By monthList = By.xpath("//span[contains(@class, 'month')]");
    private String dayString = "//td[@class='day'][text()='%d']";

    public String getDateValue() {
        return driver.findElement(inputDateField).getAttribute("value");
    }

    //Constructor
    public CalenderPage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        action.scrollByAmount(0, 500).pause(300).perform();
    }

    private void selectYear(int year) {
        driver.findElement(inputDateField).click();
        driver.findElement(switchButton).click();
        driver.findElement(switchButton).click();

        while (true) {
            List<WebElement> yearsElements = driver.findElements(yearsList);
            int firstYear = Integer.parseInt(yearsElements.getFirst().getText());
            int lastYear = Integer.parseInt(yearsElements.getLast().getText());

            if(year >= firstYear && year <= lastYear){
                driver.findElement(By.xpath(String.format(yearString, year))).click();
                break;
            } else if (year < firstYear){
                driver.findElement(prevButton).click();
            } else {
                driver.findElement(nextButton).click();
            }
        }
    }

    private void selectMonth(int monthIndex) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(monthList));
        List<WebElement> monthElementsList = driver.findElements(monthList);
        monthElementsList.get(monthIndex -1).click();
    }

    private void selectDay(int day){
        driver.findElement(By.xpath(String.format(dayString, day))).click();
    }

    public void pickDate(int day, int month, int year) {
        selectYear(year);
        selectMonth(month);
        selectDay(day);
    }
}
