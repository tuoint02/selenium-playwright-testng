package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Playwright_09_Custom_Dropdown {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeClass
    public void beforeClass(){
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(50));
        page = browser.newPage();
    }

    @Test
    public void TC_01_JQuery() {
        page.navigate("http://jqueryui.com/resources/demos/selectmenu/default.html");

        selectDropDownItem("span#speed-button","ul#speed-menu div.ui-menu-item-wrapper","Fast");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Fast");

    }

    // Viết hàm tìm locator, sau đó click vào tìm list element, sau đó duyệt qua từng item rồi gettext và click vào chọn
    private void selectDropDownItem(String parentLocator, String childLocaltor, String textItem) throws InterruptedException {
        page.locator(parentLocator).click();
        Locator allItem = page.locator(childLocaltor);


        for (WebElement item: listItems){
            if (item.getText().equals(textItem)){
                item.click();
                break;
            }
        }
    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }

}
