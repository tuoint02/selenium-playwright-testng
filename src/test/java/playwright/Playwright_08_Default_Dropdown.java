package playwright;

import com.microsoft.playwright.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Playwright_08_Default_Dropdown {
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
    public void TC_01_SauceDemo() {
        page.navigate("https://www.saucedemo.com/");

        page.locator("//input[@id='user-name']").fill("standard_user");
        page.locator("//input[@id='password']").fill("secret_sauce");
        page.locator("//input[@id='login-button']").click();

        Locator productDropdown = page.locator("select.product_sort_container");

        // Dùng value
        productDropdown.selectOption("za");
        Assert.assertEquals(productDropdown.locator("option:checked").textContent(),"Name (Z to A)");
    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }

}
