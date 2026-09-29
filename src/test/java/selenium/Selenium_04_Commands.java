package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Selenium_04_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_WebBrowser() {
        // Browser - mở ra 1 URL bất kỳ
        driver.get("https://opencart.abstracta.us/index.php?route=account/register");

        // Đong tab (dùng để handle windows)
        driver.close();

        // Đóng trình duyệt
        driver.quit();

        // Đi tìm element ở page hiện tại
        driver.findElement(By.id(""));

        // Tìm nhiều element giống nhau ở page hiện tại
        driver.findElement(By.cssSelector(""));

        //Setter = gán vào (ví dụ bỏ vào túi 10 quả táo)
        // Getter = lấy hết (VD lấy ra 5 quả táo trong cái túi)

        // Lấy ra title của page hiện tại
        driver.getTitle();

                //1- Thao tác/ verify trực tiếp (chỉ dùng 1 lần)
                Assert.assertEquals(driver.getTitle(),"Home page");

                //2- Khai báo biển để Tháo tác/ verify (dùng từ 2 lần trở lên)
                String homePageTitle = driver.getTitle();
                //VD step 05
                Assert.assertEquals(homePageTitle,"Home Page");

                //VD Step 10 gọi lại lần nữa
                Assert.assertEquals(homePageTitle,"Home Page");

        // Lấy ra source code của page hiện tại (HTML/CSS/JS/JQuery)
        driver.getPageSource();
            //Hoặc
        Assert.assertTrue(driver.getPageSource().contains("500.00$"));

        // Lấy URL của page hiện tại
        driver.getCurrentUrl();

        //Thao tác với Windown
            //Lấy ra ID của Tab/windown hiện tại
            driver.getWindowHandle();
            // Lấy ra tất cả ID của Tab/windown đang có
            driver.getWindowHandles();
            // Hoặc
            Set<String> allIDs = driver.getWindowHandles();

        // Thao tác trực tiếp
        driver.manage().deleteAllCookies();
        // Hoặc khai báo biến
        WebDriver.Options manage = driver.manage();
        manage.deleteAllCookies();

        // Hạ browser xuống thanh Taskbar
        driver.manage().window().minimize();

        // Browser phóng to lên
        driver.manage().window().maximize();

        // chế độ fullscreen
        driver.manage().window().fullscreen();

        // Lấy ra kích thước của browser (rộng - cao)
        driver.manage().window().getSize();

        // Browser mở với kích thước mong muốn (Responsive)
        driver.manage().window().setSize(new Dimension(1920, 1080));

        //
        driver.manage().window().getPosition();

        //
        driver.manage().window().setPosition(new Point(0,0));

        // Lấy ra cookies của trang hiện tại
        driver.manage().getCookies();
        // Gán cookies vào tab hiện tại
        //driver.manage().addCookie();
        //Xóa cookies bất kỳ
        //driver.manage().deleteCookie();
        driver.manage().deleteCookieNamed("h89798790unbn98j0j");

        driver.manage().deleteAllCookies();

        // Wait ngâm định để tìm Element
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().getImplicitWaitTimeout();

        // Wait ngâm định để thực thi JS
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().getScriptTimeout();

        // Wait ngâm định để page load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().getPageLoadTimeout();

        driver.manage().logs().get("");
        driver.manage().logs().getAvailableLogTypes();


    }

    @Test
    public void TC_02_WebElement() {
        //Element
        WebElement emailTextbox = driver.findElement(By.cssSelector(""));
        emailTextbox.click();

        emailTextbox.sendKeys("");

        emailTextbox.isDisplayed();

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
