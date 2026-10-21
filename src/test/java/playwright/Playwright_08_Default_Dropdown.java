package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
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

        // Dùng value or text
        productDropdown.selectOption("za");
        Assert.assertEquals(productDropdown.inputValue(),"za");
        Assert.assertEquals(productDropdown.locator("option:checked").textContent(),"Name (Z to A)");

        // Dùng value or text
        productDropdown.selectOption("Price (low to high)");
        Assert.assertEquals(productDropdown.locator("option:checked").textContent(),"Price (low to high)");

        // Index
        productDropdown.selectOption(new SelectOption().setIndex(0));
        Assert.assertEquals(productDropdown.locator("option:checked").textContent(),"Name (A to Z)");

        // Value
        productDropdown.selectOption(new SelectOption().setValue("za"));
        Assert.assertEquals(productDropdown.locator("option:checked").textContent(),"Name (Z to A)");

        // Label Text
        productDropdown.selectOption(new SelectOption().setLabel("Price (low to high)"));
        Assert.assertEquals(productDropdown.locator("option:checked").textContent(),"Price (low to high)");

        // Is multiple
        Assert.assertNull(productDropdown.getAttribute("multiple"));
        System.out.println("Dropdown is multiple = " +productDropdown.getAttribute("multiple"));
        //Assert.assertEquals(productDropdown.getAttribute("multiple"), null); -- Cách khác

        // Kiểm tra xem có bao nhiêu items
        Locator productDropdownOptions = productDropdown.locator("option");
        Assert.assertEquals(productDropdownOptions.count(),4);
        System.out.println("Dropdown option = " +productDropdownOptions.count());

    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }

}
