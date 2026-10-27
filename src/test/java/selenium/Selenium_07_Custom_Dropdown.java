package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Selenium_07_Custom_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Sauce() throws InterruptedException {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        selectDropDownItem("span#speed-button","ul#speed-menu div.ui-menu-item-wrapper","Fast");
        Thread.sleep(2000);
        selectDropDownItem("span#speed-button","ul#speed-menu div.ui-menu-item-wrapper","Slow");
        Thread.sleep(2000);
        selectDropDownItem("span#speed-button","ul#speed-menu div.ui-menu-item-wrapper","Fast");
        Thread.sleep(2000);

    }

    private void selectDropDownItem(String parentLocator, String childLocaltor, String textItem) throws InterruptedException {
        driver.findElement(By.cssSelector(parentLocator)).click();

        List<WebElement> listItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childLocaltor)));

        Thread.sleep(1000);

        for (WebElement item: listItems){
            if (item.getText().equals(textItem)){
                item.click();
                break;
            }
        }
    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
