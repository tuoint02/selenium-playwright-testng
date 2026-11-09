package selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Selenium_10_Alert {
    WebDriver driver;
    Alert alert;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_alert_livetechpanda() {
        driver.get("https://live.techpanda.org/index.php/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.cssSelector("a[title='Xperia']~div>div.actions a.link-compare")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
        driver.findElement(By.cssSelector("a[title='IPhone']~div>div.actions a.link-compare")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']")).isDisplayed());
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']~div>div.actions a.link-compare")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();

        // Switch vào Alert
        alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());

        // Get text ra để verify text title của alert
        Assert.assertEquals(alert.getText(),"Are you sure you would like to remove all products from your comparison?");

        // Accept
        alert.accept();
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
    }

    @Test
    public void TC_02_() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        String text  = "Automation";
        alert.sendKeys(text );
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + text);
    }

    @Test
    public void TC_03_() {
        //driver.get("http://the-internet.herokuapp.com/basic_auth");
        //Cách 1: truyền thẳng user/pass vào url
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
        //Cách 2:

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
