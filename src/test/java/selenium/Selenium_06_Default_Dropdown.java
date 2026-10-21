package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Selenium_06_Default_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_SauceDemo() {
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
    public void TC_02_Rode() {
        driver.get("https://rode.com/en-au/support/where-to-buy");
        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("Ho Chi Minh");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        // Chờ cho 16 dealer xuất hiện trong vòng 15s (Không quan tâm là hiển thị/ không hiển thị)
        //Lấy hết các deadler này đưa vào 1 danh sách element
        List<WebElement> dealerList = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//h3[text()='Dealers']/following-sibling::div//h4"),16));

        Assert.assertEquals(dealerList.size(),16);

        for (WebElement dealer : dealerList){
            System.out.println("Dealer Name = " + dealer.getText());
        }
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
