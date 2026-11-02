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

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

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
    public void TC_01_JQuery() throws InterruptedException {
        page.navigate("http://jqueryui.com/resources/demos/selectmenu/default.html");

        selectDropDownItem("span#speed-button","ul#speed-menu div.ui-menu-item-wrapper","Fast");
        Assert.assertEquals(page.locator("span#speed-button>span.ui-selectmenu-text").textContent(),"Fast");

        selectDropDownItem("span#speed-button","ul#speed-menu div.ui-menu-item-wrapper","Slow");
        Assert.assertEquals(page.locator("span#speed-button>span.ui-selectmenu-text").textContent(),"Slow");

        selectDropDownItem("span#speed-button","ul#speed-menu div.ui-menu-item-wrapper","Faster");
        Assert.assertEquals(page.locator("span#speed-button>span.ui-selectmenu-text").textContent(),"Faster");
    }

    @Test
    public void TC_02_ReactJS() throws InterruptedException {
        page.navigate("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectDropDownItem("div.dropdown","div.item>span.text","Jenny Hess");
        Assert.assertEquals(page.locator("div.divider.text").textContent(),"Jenny Hess");

        selectDropDownItem("div.dropdown","div.item>span.text","Elliot Fu");
        Assert.assertEquals(page.locator("div.divider.text").textContent(),"Elliot Fu");

        selectDropDownItem("div.dropdown","div.item>span.text","Stevie Feliciano");
        Assert.assertEquals(page.locator("div.divider.text").textContent(),"Stevie Feliciano");

    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        page.navigate("https://mikerodham.github.io/vue-dropdowns/");

        selectDropDownItem("li.dropdown-toggle","ul.dropdown-menu a","First Option");
        assertThat(page.locator("li.dropdown-toggle")).hasText("First Option");

    }

    @Test
    public void TC_04_Editable() throws InterruptedException {
        page.navigate("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        editableDropDownItem("input.search","div.item>span.text","Algeria");
        Thread.sleep(2000);
        Assert.assertEquals(page.locator("div.divider.text").textContent(),"Algeria");

    }

    @Test
    public void TC_05_Editable_Finpeace() throws InterruptedException {
        page.navigate("https://sps.finpeace.vn/tools/sktccn");

        editableDropDownItem("input#job_id", "div.ant-select-item-option-content", "Chủ doanh nghiệp");
        Thread.sleep(2000);
        Assert.assertEquals(page.locator("//input[@id='job_id']/parent::span/following-sibling::span[@class='ant-select-selection-item']").textContent(), "Chủ doanh nghiệp");

    }

    //  Viết hàm tìm locator, sau đó click vào tìm list element, sau đó duyệt qua từng item rồi gettext và click vào chọn
    private void selectDropDownItem(String parentLocator, String childLocaltor, String textItem) throws InterruptedException {
        // Click vào cho xổ ra danh sách các item bên trong dropdown
        page.locator(parentLocator).click();

        // Cách 1 - Không dùng vòng lặp, dùng
        page.locator(childLocaltor).getByText(textItem, new Locator.GetByTextOptions().setExact(true)).click();

        /* Cách 2- Dùng vòng lặp
        // Tìm locator khớp và trả về tất cả các item bên trong
        Locator allItem = page.locator(childLocaltor);
        // Dùng vòng lặp duyệt qua từng item
        for (int i = 0; i < allItem.count(); i++) {
            // Dùng nth để lấy ra từng element và gán tạm vào biến item
            Locator item = allItem.nth(i);
            // Kiểm tra điều kiện nếu khớp với text truyền vào thì click vào element đó và thoát khỏi vòng lặp
            if (item.innerText().equals(textItem)){
                item.click();
                break;
            }
        } */

    }

    private void editableDropDownItem(String parentLocator, String childLocaltor, String textItem) throws InterruptedException {
        // Click vào cho xổ ra danh sách các item bên trong dropdown
        page.locator(parentLocator).fill(textItem);
        page.locator(childLocaltor).getByText(textItem, new Locator.GetByTextOptions().setExact(true)).click();
    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }

}
