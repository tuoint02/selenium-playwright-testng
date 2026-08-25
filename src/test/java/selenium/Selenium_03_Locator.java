package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Selenium_03_Locator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://opencart.abstracta.us/index.php?route=account/register");
    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("input-lastname")).sendKeys("Automation");
        driver.findElement(By.id("input-password")).sendKeys("Pass1234@@@@");
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("agree"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("telephone"));
        driver.findElement(By.name("email"));
        driver.findElement(By.name("firstname"));
    }

    @Test
    public void TC_04_Tagname() {
        int textboxSize = driver.findElements(By.tagName("input")).size();
        System.out.println("TagName Textbox number = " + textboxSize);

    }

    @Test
    public void TC_05_Link() {
        driver.findElement(By.linkText("Wish List"));
        driver.findElement(By.linkText("Recurring payments"));
        driver.findElement(By.linkText("Forgotten Password"));
        driver.findElement(By.linkText("Transactions"));

    }

    @Test
    public void TC_06_PartialLink() {
        driver.findElement(By.partialLinkText("Wish"));
        driver.findElement(By.partialLinkText("List"));
        driver.findElement(By.partialLinkText("Recurring"));
        driver.findElement(By.partialLinkText("Forgotten"));

    }

    @Test
    public void TC_07_Css() {
        driver.findElement(By.cssSelector("input#input-firstname"));
        driver.findElement(By.cssSelector("input[id='input-firstname']"));
        driver.findElement(By.cssSelector("a.agree"));
        driver.findElement(By.cssSelector("input[name='telephone']"));
        int textboxSize = driver.findElements(By.cssSelector("input")).size();
        System.out.println(" CSS Textbox number = " + textboxSize);
        driver.findElement(By.cssSelector("a[href*='account/account']"));
        driver.findElement(By.cssSelector("a[href*='https://opencart.abstracta.us:443/index.php?route=account/account']"));
    }

    @Test
    public void TC_08_XPath() {
        driver.findElement(By.xpath("//input[@id='input-confirm']")); //ID
        driver.findElement(By.xpath("//a[@class='agree']")); //Class
        driver.findElement(By.xpath("//input[@name='telephone']")); //Name
        int textboxSize = driver.findElements(By.xpath("//input")).size();
        System.out.println("Xpath Textbox number = " + textboxSize);

        driver.findElement(By.xpath("//a[@href='https://opencart.abstracta.us:443/index.php?route=account/account']"));
        driver.findElement(By.xpath("//a[text()='My Account']"));

        driver.findElement(By.xpath("//a[contains(@href,'account/account')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Recurring')]"));


    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
