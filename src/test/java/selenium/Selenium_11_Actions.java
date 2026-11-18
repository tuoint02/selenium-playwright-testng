package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class Selenium_11_Actions {
    WebDriver driver;
    Actions actions; // Khai báo

    String osName = System.getProperty("os.name"); // khai báo dùng cho TC_04

    JavascriptExecutor jsExecutor; // Khai báo dùng cho TC_05


    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        actions = new Actions(driver); // Khởi tạo dùng cho TC_04

        jsExecutor = (JavascriptExecutor) driver; // Ép kiểu tường minh dùng cho TC_05
    }

    @Test
    public void TC_01_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        actions.pause(Duration.ofSeconds(3)).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Myntra_Hover() {
        driver.get("http://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navContent']//a[text()='Kids']"))).pause(Duration.ofSeconds(2)).perform();
        actions.click(driver.findElement(By.xpath("//div[@class='desktop-navContent']//a[text()='Home & Bath']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(),"Kids Home Bath");
    }

    @Test
    public void TC_03_Block() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-state-default"));

        // Chọn từ 9 đến 20
        actions.clickAndHold(numbers.get(8)).pause(2).moveToElement(numbers.get(19)).pause(2).release().perform();
        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(), 12);
    }

    @Test
    public void TC_04_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-state-default"));

        // Chọn nhiều block ngẫu nhiên
        Keys keys;
        if (osName.startsWith("Mac")){
            keys = Keys.COMMAND;
        } else {
            keys = Keys.CONTROL;
        }
        actions.keyDown(Keys.CONTROL).perform(); // Nhấn Ctrl xuống

        // chọn các số cần click
        actions.click(numbers.get(8))
                .pause(Duration.ofSeconds(1))
                .click(numbers.get(10))
                .pause(Duration.ofSeconds(1))
                .click(numbers.get(15))
                .pause(Duration.ofSeconds(1))
                .click(numbers.get(18))
                .pause(Duration.ofSeconds(1))
                .click(numbers.get(20))
                .pause(Duration.ofSeconds(1))
                .perform();

        actions.keyUp(keys).perform(); // Nhả Ctrl ra
        Assert.assertEquals(driver.findElements(By.cssSelector("ol#selectable>li.ui-selected")).size(), 5);
    }

    @Test
    public void TC_05_Double() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']"))); // Dùng khi chạy với trình duyệt FireFox, các trình duyệt khác không cần

        actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
