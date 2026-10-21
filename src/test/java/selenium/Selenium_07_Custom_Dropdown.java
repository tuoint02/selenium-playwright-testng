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
    public void TC_01_Sauce() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        // 1 - Click vào dropdown
        driver.findElement(By.cssSelector("span#speed-button")).click();

        // 2- Chờ cho tất cả các item được load ra
        // Bắt được locator nào đại diện cho tất cả các item
        // Bắt đến thẻ nào chứa text của item
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div.ui-menu-item-wrapper")));
        // 3 - Duyệt qua hết tất cả các item
        List<WebElement> listItems = driver.findElements(By.cssSelector("ul#speed-menu div.ui-menu-item-wrapper"));
        for (WebElement item: listItems){

            // 4 - Kiểm tra label text nếu đúng thì click chọn/ nếu không đúng thì duyệt tiếp
            String itemText = item.getText();
            System.out.println("itemText = " +itemText);
            if (itemText.equals("Fast")){
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
