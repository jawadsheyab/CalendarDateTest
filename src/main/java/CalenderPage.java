import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class CalenderPage {

    private WebDriver driver;
    private Actions action;

    private By inputDateField = By.id("txtStartDate");
    private By prevButton = By.cssSelector("div[style=''] th[class='prev']");
    private By nextButton = By.cssSelector("div[style=''] th[class='next']");
    private By switchButton = By.cssSelector("div[style=''] th[class='datepicker-switch']");
    private By yearsList = By.xpath("//span[contains(@class, 'year')]");
    private String yearString = "//span[contains(@class, 'year') and (text()='%d')]";
    private By monthList = By.xpath("//span[contains(@class, 'month')]");

    //Constructor
    public CalenderPage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        action.scrollByAmount(0, 500).pause(300).perform();
    }

    public void selectYear(int year) {
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

    public void selectMonth(int monthIndex) {

    }
}
