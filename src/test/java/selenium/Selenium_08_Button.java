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

import java.time.Duration;
import java.util.List;

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

        By rearSideCheckboxBy = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");
        By leatherTrimCheckboxBy = By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input");
        By dualZoneCheckboxBy = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        // Click chọn rearSideCheckboxBy
        if (!driver.findElement(rearSideCheckboxBy).isSelected()){
            driver.findElement(rearSideCheckboxBy).click();
        }

        // Verify chọn hoặc chưa chọn
        Assert.assertTrue(driver.findElement(rearSideCheckboxBy).isSelected());
        Assert.assertTrue(driver.findElement(leatherTrimCheckboxBy).isSelected());
        Assert.assertFalse(driver.findElement(dualZoneCheckboxBy).isSelected());

        // Verify disabled/ enabled
        Assert.assertTrue(driver.findElement(rearSideCheckboxBy).isEnabled());
        Assert.assertFalse(driver.findElement(leatherTrimCheckboxBy).isEnabled());
        Assert.assertTrue(driver.findElement(dualZoneCheckboxBy).isEnabled());

        // Click chọn dual-Zone Checkbox
        if (!driver.findElement(dualZoneCheckboxBy).isSelected()){
            driver.findElement(dualZoneCheckboxBy).click();
        }

        Assert.assertTrue(driver.findElement(dualZoneCheckboxBy).isSelected());

    }

    @Test
    public void TC_03_Jotform_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        // Kiểm tra toàn bộ checkbox chưa chọn --> dùng vòng lặp
        for (WebElement checkbox : checkboxes){
            Assert.assertFalse(checkbox.isSelected());
        }

        // Chọn hết toàn bộ
        for (WebElement checkbox : checkboxes){
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }

        // Kiểm tra toàn bộ checkbox được chọn rồi (selected)
        for (WebElement checkbox : checkboxes){
            Assert.assertTrue(checkbox.isSelected());
        }

        // Bỏ chọn hết toàn bộ
        for (WebElement checkbox : checkboxes){
            if (checkbox.isSelected()){
                checkbox.click();
            }
        }

        // Chọn 1 checkbox nào đó trong tất cả
        for (WebElement checkbox : checkboxes){
            if (!checkbox.isSelected() && checkbox.getDomProperty("value").equals("Diabetes")){
                checkbox.click();
            }
        }

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
