package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Playwright_12_Alert {
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
    public void TC_01_alert_livetechpanda() {
        page.navigate("https://live.techpanda.org/index.php/");
        // Alert = Dialog
        //page.onDialog();
       // page.onceDialog();
        //

        page.locator("//a[text()='Mobile']").click();

        page.locator("a[title='Xperia']~div>div.actions a.link-compare").click();
        Assert.assertTrue(page.locator("//span[text()='The product Sony Xperia has been added to comparison list.']").isVisible());

        page.locator("a[title='IPhone']~div>div.actions a.link-compare").click();
        Assert.assertTrue(page.locator("//span[text()='The product IPhone has been added to comparison list.']").isVisible());

        page.locator("a[title='Samsung Galaxy']~div>div.actions a.link-compare").click();
        Assert.assertTrue(page.locator("//span[text()='The product Samsung Galaxy has been added to comparison list.']").isVisible());

        page.locator("//a[text()='Clear All']").click();

        // Viết code xử lý Alert trước rồi mới click cho Alert xuất hiện sau
        page.onDialog(dialog -> {
            //Assert.assertTrue(dialog.type(),"confirm");

            // Verify message
            Assert.assertEquals(dialog.message(),"Are you sure you would like to remove all products from your comparison?");

            //Accept
            dialog.accept();
            Assert.assertTrue(page.locator("//span[text()='The comparison list was cleared.']").isVisible());
        });
    }

    @Test
    public void TC_02() {
        page.navigate("https://automationfc.github.io/basic-form/index.html");
        String text = "Automation";

        page.onDialog(dialog -> {
            Assert.assertEquals(dialog.message(),"I am a JS prompt");
            dialog.accept(text);
        });

        page.locator("//button[text()='Click for JS Prompt']").click();


    }

    @Test
    public void TC_03() {
        page.navigate("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(page.locator("//p[contains(text(),'Congratulations! You must have the proper credentials.')]").isVisible());

    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }

}
