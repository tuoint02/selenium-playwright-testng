package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Selenium_09_Custom_Checkbox_Radio {
    WebDriver driver;

    JavascriptExecutor jsExecutor; //Khai báo thư viện Javascript Executor - Khai báo cho cách 2 TC_01_Custom_Radio_Checkbox

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        jsExecutor = (JavascriptExecutor) driver; // Ép kiểu tường minh - Khởi tạo Javascript Executor - Khởi tạo cho cách 2 TC_01_Custom_Radio_Checkbox

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Custom_Radio_Checkbox() {
        driver.get("https://login.ubuntu.com/");

        /* Cách 1: Dùng thẻ label để click chọn - Dùng thẻ input để verify đã chọn radio
        // Cách này tăng số lượng dòng code lên + dễ gây hiểu nhầm khi đọc code
        By newUserRadioClick = By.cssSelector("label.new-user");
        By newUserRadioVerify = By.cssSelector("input#id_new_user");

        if (!driver.findElement(newUserRadioClick).isSelected()) {
            driver.findElement(newUserRadioClick).click();
        }
        Assert.assertTrue(driver.findElement(newUserRadioVerify).isSelected()); */



        // Cách 2: Thẻ input dùng để click + verify
        // Hàm click của Selenium bị lỗi vì nó luôn check clickable của 1 element trước
        // Hàm click của Javascript Excutor nó không quan tâm element có bị che bị ẩn không ==> Work Around
        By newUserRadio = By.cssSelector("input#id_new_user");
        By acceptCheckbox = By.cssSelector("input#id_accept_tos");

        if (!driver.findElement(newUserRadio).isSelected()) {
            jsExecutor.executeScript("arguments[0].click();", driver.findElement(newUserRadio));
        }
        Assert.assertTrue(driver.findElement(newUserRadio).isSelected());

        if (!driver.findElement(acceptCheckbox).isSelected()) {
            jsExecutor.executeScript("arguments[0].click();", driver.findElement(acceptCheckbox));
        }
        Assert.assertTrue(driver.findElement(newUserRadio).isSelected());
    }

    @Test
    public  void TC_02_Google_Docs(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By quangnamCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");

        // Verify Deselected
        Assert.assertEquals(driver.findElement(quangnamCheckbox).getDomAttribute("aria-checked"),"false");

        // Click
        driver.findElement(quangnamCheckbox).click();

        // Verify Selected
        Assert.assertEquals(driver.findElement(quangnamCheckbox).getDomAttribute("aria-checked"),"true");

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
