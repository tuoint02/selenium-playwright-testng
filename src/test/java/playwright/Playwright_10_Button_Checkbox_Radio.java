package playwright;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Playwright_10_Button_Checkbox_Radio {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeClass
    public void beforeClass(){
        playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(50));
        page = browser.newPage();
        page.navigate("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Button() {
        page.navigate("https://www.fahasa.com/customer/account/create");

        page.locator("li.popup-login-tab-login").click();
        Locator loginButton = page.locator("button.fhs-btn-login");
        // Verify text
        Assert.assertEquals(loginButton.textContent(),"Đăng nhập"); //Cách 1
        assertThat(loginButton).hasText("Đăng nhập"); // Cách 2

        // Verify button có trạng thái là disable
        Assert.assertTrue(loginButton.isDisabled()); // Cách 1
        Assert.assertFalse(loginButton.isEnabled()); // Cách 2 - Not recommend
        assertThat(loginButton).isDisabled(); // Cách 3
        assertThat(loginButton).not().isEnabled(); // Cách  4 - Not recommend vì dễ gây hiểu lầm

        // Verify màu nền là xám
        assertThat(loginButton).hasCSS("background-color","rgba(0, 0, 0, 0)"); // Cách 1

        page.locator("input#login_username").pressSequentially("test@gmail.com");
        page.locator("input#login_password").pressSequentially("123456");

        // Wait login button có thể click được

        // Verify button có trạng thái là enable
        Assert.assertTrue(loginButton.isEnabled()); // Cách 1
        Assert.assertFalse(loginButton.isDisabled()); // Cách 2 - Not recommend
        assertThat(loginButton).not().isDisabled(); // Cách 3
        assertThat(loginButton).isEnabled(); // Cách 4 - Not recommend

        // Verify màu nền là đỏ
        assertThat(loginButton).hasCSS("background-color","rgb(201, 33, 39)"); // Cách 1

    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }

}
