package playwright;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Playwright_12_Custom_Checkbox_Radio {
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
    public void TC_01_Custom_Radio_Checkbox() {
        page.navigate("https://login.ubuntu.com/");
        Locator newUserRadio = page.locator("input#id_new_user");
        Locator acceptCheckbox = page.locator("input#id_accept_tos");

        if (!newUserRadio.isChecked()){
            newUserRadio.dispatchEvent("click");
            //newUserRadio.evaluate("el => el.click"); // Cách 2
        }

        Assert.assertTrue(newUserRadio.isChecked());
        assertThat(newUserRadio).isChecked(); // Cách 2 verify

        if (!acceptCheckbox.isChecked()){
            acceptCheckbox.dispatchEvent("click");
            //acceptCheckbox.evaluate("el => el.click"); // Cách 2
        }

        Assert.assertTrue(acceptCheckbox.isChecked());
        assertThat(acceptCheckbox).isChecked(); // Cách 2 verify
    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }

}
