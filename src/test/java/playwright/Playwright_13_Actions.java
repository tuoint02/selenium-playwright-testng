package playwright;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Playwright_13_Actions {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeClass
    public void beforeClass(){
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(1000));
        page = browser.newPage();
    }

    @Test
    public void TC_01_Hover() {
        page.navigate("https://automationfc.github.io/jquery-tooltip/");
        page.locator("input#age").hover();
        assertThat(page.locator("div.ui-tooltip-content")).hasText("We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover() {
        page.navigate("https://www.telerik.com/support/demos");
        page.locator("button.TK-Products-Menu-Item-Button").hover();
        page.locator("//a[text()='Test Studio']").click();
        Assert.assertTrue(page.locator("//h2[text()='Automated Testing That Just Works']").isVisible());
    }

    @Test
    public void TC_03_ClickAndHold() {
        page.navigate("https://automationfc.github.io/jquery-selectable/");
        List<Locator> numbers = page.locator("ol#selectable>li.ui-state-default").all();
        numbers.get(8).hover();
        page.mouse().down();
        numbers.get(19).hover();
        page.mouse().up();

        Assert.assertEquals(page.locator("ol#selectable>li.ui-selected").all().size(),12);
    }

    @Test
    public void TC_04_() {
        page.navigate("");
    }

    @Test
    public void TC_05_() {
        page.navigate("");
    }

    @Test
    public void TC_06_() {
        page.navigate("");
    }

    @Test
    public void TC_07_() {
        page.navigate("");
    }

    @Test
    public void TC_08_() {
        page.navigate("");
    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }

}
