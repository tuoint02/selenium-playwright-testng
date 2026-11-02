package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;

public class Selenium_08_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Fahasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginBtnBy = By.cssSelector("button.fhs-btn-login");

        // Verify text
        Assert.assertEquals(driver.findElement(loginBtnBy).getText(),"Đăng nhập");

        // Verify button có trạng thái là disable
        Assert.assertFalse(driver.findElement(loginBtnBy).isEnabled());

        // Verify màu nền là xám
        Assert.assertEquals(Color.fromString(driver.findElement(loginBtnBy)
                .getCssValue("background-color")).asHex().toUpperCase(),"#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");

        // Wait login button có thể click được
        WebElement loginButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(loginBtnBy));

        // Verify button có trạng thái là enable
        Assert.assertTrue(loginButton.isEnabled());

        // Verify màu nền là đỏ
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toUpperCase(),"#C92127");
    }

    @Test
    public void TC_02_KendoUI_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
