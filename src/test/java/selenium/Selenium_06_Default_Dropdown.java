package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Selenium_06_Default_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        By sortDropdownBy = By.cssSelector("select.product_sort_container");

        //Dropdown không phải multiple

        // Chọn
        //sortDropdown.selectByIndex(2);
        //sortDropdown.selectByValue("az");
        //sortDropdown.selectByContainsVisibleText("high to low");
        new Select(driver.findElement(sortDropdownBy)).selectByVisibleText("Name (Z to A)");

        // Bỏ chọn
        //sortDropdown.deselectByIndex(2);
        //sortDropdown.deselectByValue("az");
        //sortDropdown.deSelectByContainsVisibleText("high to low");
        //sortDropdown.deselectByVisibleText("Name (Z to A)");

        // Kiểm tra chọn thành công
        Assert.assertEquals(new Select(driver.findElement(sortDropdownBy)).getFirstSelectedOption().getText(),"Name (Z to A)");


        // Kiểm tra số lượng item bao nhiêu
        //Assert.assertEquals(sortDropdown.getOptions().size(),4);



    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
