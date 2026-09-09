package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Playwright_03_Locator {
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
    public void TC_01_Role() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Yes"));
        page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Account"));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue"));
    }

    @Test
    public void TC_02_Label() {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        page.getByLabel("First Name");
        page.getByLabel("E-Mail");
        page.getByLabel("Yes");
        page.getByLabel("Subscribe");
    }

    @Test
    public void TC_03_Placeholder() throws InterruptedException {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        page.getByPlaceholder("Telephone").fill("0359382719");
        page.getByPlaceholder("Password Confirm").fill("Pass12345");
        page.getByPlaceholder("Search");
        page.getByPlaceholder("First Name").fill("Nguyen");
    }

    @Test
    public void TC_04_Text() throws InterruptedException {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        page.getByText("Your Personal Details");
        page.getByText("First Name");
        page.getByText("Forgotten Password");
        page.getByText("Recurring payments");
        page.getByText("Subscribe");

    }

    @Test
    public void TC_05_AltText() throws InterruptedException {
        page.navigate("https://opencart.abstracta.us/index.php?route=common/home");
        page.getByAltText("MacBook");
        page.getByAltText("iPhone");
        page.getByAltText("Apple Cinema 30&quot;");
        page.getByAltText("Canon EOS 5D");
    }

    @Test
    public void TC_06_Title() throws InterruptedException {
        page.navigate("https://opencart.abstracta.us/index.php?route=common/home");
        page.getByTitle("Shopping Cart");
        page.getByTitle("My Account");
        page.getByTitle("Checkout");

    }

    @Test
    public void TC_07_TestID() throws InterruptedException {
        page.navigate("https://demo-bank.vercel.app/");
        page.getByTestId("login-input");
        page.getByTestId("password-input");
        page.getByTestId("login-button");
    }

    @Test
    public void TC_08_Css() throws InterruptedException {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        page.locator("form input#input-firstname");
    }

    @Test
    public void TC_09_Xpath() throws InterruptedException {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        page.locator("//input[@id='input-firstname']");
    }

    @Test
    public void TC_10_Xpath_Text() throws InterruptedException {
        page.navigate("https://opencart.abstracta.us/index.php?route=account/register");
        page.locator("//label[text()='First Name']");
        page.locator("//p[contains(text(),'If you already have an account with us, please login at the')]");
        page.locator("//input[@id='input-firstname' or @name='firstname']");
        page.locator("");
    }


    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }

}
