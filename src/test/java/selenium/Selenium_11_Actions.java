package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class Selenium_11_Actions {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        actions = new Actions(driver);
    }

    @Test
    public void TC_01_() {

    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
