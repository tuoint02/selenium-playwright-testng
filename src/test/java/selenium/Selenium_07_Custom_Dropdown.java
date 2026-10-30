package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Selenium_07_Custom_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_JQuery() throws InterruptedException {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        selectDropDownItem("span#speed-button","ul#speed-menu div.ui-menu-item-wrapper","Fast");
        //Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Fast");

        selectDropDownItem("span#speed-button","ul#speed-menu div.ui-menu-item-wrapper","Slow");
        //Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Slow");

        selectDropDownItem("span#speed-button","ul#speed-menu div.ui-menu-item-wrapper","Faster");
        //Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Faster");

    }

    @Test
    public void TC_02_ReactJS() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectDropDownItem("div.dropdown","div.item>span.text","Jenny Hess");
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Jenny Hess");

        selectDropDownItem("div.dropdown","div.item>span.text","Elliot Fu");
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Elliot Fu");

        selectDropDownItem("div.dropdown","div.item>span.text","Stevie Feliciano");
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Stevie Feliciano");

    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectDropDownItem("li.dropdown-toggle","ul.dropdown-menu a","First Option");
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");

        selectDropDownItem("li.dropdown-toggle","ul.dropdown-menu a","Second Option");
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");

        selectDropDownItem("li.dropdown-toggle","ul.dropdown-menu a","Third Option");
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");

    }

    @Test
    public void TC_04_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        // Cách 1: Chọn option giống case 1,2,3
//        selectDropDownItem("div.dropdown","div.item>span.text","Algeria");
//        Thread.sleep(2000);
//        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Algeria");

        // Cách 2: Nhập text để tìm kiếm
        editDropDownItem("input.search","div.item>span.text","Algeria");
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Algeria");

    }

    @Test
    public void TC_05_Editable_Finpeace() throws InterruptedException {
        driver.get("https://sps.finpeace.vn/tools/sktccn");

        editDropDownItem("input#job_id", "div.ant-select-item-option-content", "Chủ doanh nghiệp");
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='job_id']/parent::span/following-sibling::span[@class='ant-select-selection-item']")).getText(), "Chủ doanh nghiệp");

    }

    // Mục đích của hàm này là tìm locator, sau đó click vào tìm list element, sau đó duyệt qua từng item rồi gettext và click vào chọn
    private void selectDropDownItem(String parentLocator, String childLocaltor, String textItem) throws InterruptedException {
        driver.findElement(By.cssSelector(parentLocator)).click();

        List<WebElement> listItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childLocaltor)));

        //Thread.sleep(1000);

        for (WebElement item: listItems){
            if (item.getText().equals(textItem)){
                item.click();
                break;
            }
        }
    }

    // Hàm duyệt dropdown dạng Editable
    private void editDropDownItem(String parentLocator, String childLocaltor, String textItem) throws InterruptedException {
        driver.findElement(By.cssSelector(parentLocator)).clear();
        driver.findElement(By.cssSelector(parentLocator)).sendKeys(textItem);

        List<WebElement> listItems = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childLocaltor)));

        //Thread.sleep(1000);

        for (WebElement item: listItems){
            if (item.getText().equals(textItem)){
                item.click();
                break;
            }
        }
    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
