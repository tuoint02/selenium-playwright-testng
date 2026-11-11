package playwright;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Playwright_11_Checkbox_Radio {
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
    public void TC_01_KendoUI_Checkbox() {
        page.navigate("https://demos.telerik.com/kendo-ui/checkbox/index");
        Locator rearSiteCheckbox =  page.locator("//label[text()='Rear side airbags']/preceding-sibling::span/input");
        Locator leatherTrimCheckbox =  page.locator("//label[text()='Leather trim']/preceding-sibling::span/input");
        Locator dualZoneCheckbox =  page.locator("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        // Click chọn hoặc bỏ chọn
        rearSiteCheckbox.check();
        leatherTrimCheckbox.check();
        dualZoneCheckbox.setChecked(true); //check
        dualZoneCheckbox.setChecked(false); //uncheck

        // Verify chọn hoặc chưa chọn - Cách 1
        Assert.assertTrue(rearSiteCheckbox.isChecked()); //check
        Assert.assertTrue(leatherTrimCheckbox.isChecked()); //check
        Assert.assertFalse(dualZoneCheckbox.isChecked()); //uncheck

        // Verify cách 2
        assertThat(rearSiteCheckbox).isChecked();  //check
        assertThat(leatherTrimCheckbox).isChecked(); //check
        assertThat(dualZoneCheckbox).not().isChecked(); //uncheck

    }

    @Test
    public void TC_02_Jotform_Checkbox() {
        page.navigate("https://automationfc.github.io/multiple-fields/");

        // Lấy hết tất cả danh sách checkbox
        List<Locator> checkboxes = page.locator("input[type='checkbox']").all(); // Hàm all trả về list locator

        // Kiểm tra toàn bộ checkbox chưa chọn
        for (Locator checkbox: checkboxes){
            Assert.assertFalse(checkbox.isChecked()); // Cách 1
            assertThat(checkbox).not().isChecked(); // Cách 2
        }

        // Chọn hết toàn bộ
        for (Locator checkbox: checkboxes){
            checkbox.check(); // Cách 1
            //checkbox.setChecked(true); // Cách 2
        }

        // Kiểm tra toàn bộ checkbox được chọn rồi (selected)
        for (Locator checkbox: checkboxes){
            Assert.assertTrue(checkbox.isChecked()); // Cách 1
            assertThat(checkbox).isChecked(); // Cách 2
        }

        // Bỏ chọn hết toàn bộ
        for (Locator checkbox: checkboxes){
            checkbox.uncheck();
            //checkbox.setChecked(false); // Cách 2

        }

        // Chọn 1 checkbox nào đó trong tất cả
        for (Locator checkbox: checkboxes){
            if (!checkbox.isChecked() && checkbox.getAttribute("value").startsWith("Diabetes")){
                checkbox.setChecked(true);
            }
        }

    }

    @Test
    public void TC_03_RadioButton() {
        page.navigate("https://demos.telerik.com/kendo-ui/radiobutton/index");

        Locator petrol18Radio = page.locator("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::span/input");
        Locator petrol14Radio = page.locator("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::span/input");

        // Chọn
        petrol18Radio.check();

        // Verify
        Assert.assertTrue(petrol18Radio.isChecked()); // Cách 1
        Assert.assertFalse(petrol14Radio.isChecked()); // Cách 1

        assertThat(petrol18Radio).isChecked();// Cách 2
        assertThat(petrol14Radio).not().isChecked();// Cách 2

        // Bỏ chọn
        petrol14Radio.check();

        // Verify
        Assert.assertFalse(petrol18Radio.isChecked());
        Assert.assertTrue(petrol14Radio.isChecked());

        assertThat(petrol18Radio).not().isChecked();// Cách 2
        assertThat(petrol14Radio).isChecked();// Cách 2
    }

    @AfterClass
    public void afterClass(){
        browser.close();
        playwright.close();
    }

}
