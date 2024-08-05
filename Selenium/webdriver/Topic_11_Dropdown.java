package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_11_Dropdown {
    WebDriver driver;
    Select select;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;

    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        jsExecutor = (JavascriptExecutor) driver;
    }
    //@Test
    public void TC_01_Facebook_SignUp(){
        driver.get("https://www.facebook.com/reg");
        select = new Select(driver.findElement(By.cssSelector("select#day")));
        select.selectByVisibleText("12");
        // verify đã select thành công chưa
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"12");
        // verify dropdown is single
        Assert.assertFalse(select.isMultiple());

        select = new Select(driver.findElement(By.cssSelector("select#month")));
        select.selectByVisibleText("Jun");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Jun");

        select = new Select(driver.findElement(By.cssSelector("select#year")));
        select.selectByVisibleText("1982");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"1982");
    }
    //@Test
    public void TC_01_NopCommerce(){
        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("anh");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("vu");
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).selectByVisibleText("16");
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText("December");
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText("1982");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("anhanh@anh.com");
        driver.findElement(By.cssSelector("input#Company")).sendKeys("NashTech");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");

        driver.findElement(By.cssSelector("a.ico-account")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"),"anh");
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"),"vu");
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"),"anhanh@anh.com");
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"),"NashTech");
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).getFirstSelectedOption().getText(),"16");
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(),"December");
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).getFirstSelectedOption().getText(),"1982");

    }
    //@Test
    public void TC_02_Rode() {
        driver.get("https://rode.com/en/support/where-to-buy");
        sleepInSecond(2);
        //click vào allow cookie
        driver.findElement(By.cssSelector("button.cookie__bar__buttons__button__reqd")).click();
        sleepInSecond(1);
        // click ok on confirmation popup
        driver.findElement(By.cssSelector("button.swal2-confirm")).click();
        sleepInSecond(1);
        // chọn trong dropdown
        driver.findElement(By.cssSelector("select#country")).click();
        Select select = new Select(driver.findElement(By.cssSelector("select#country")));
        select.selectByVisibleText("Vietnam");
        Assert.assertFalse(select.isMultiple());
        // enter vào textbox
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        // nhấn search button
        driver.findElement(By.cssSelector("button.btn-default")).click();
        // in ra title cho result
        List<WebElement> searchResults= driver.findElements(By.xpath("//h3[text()='Dealers']//following-sibling::div//h4"));
        Assert.assertEquals(searchResults.size(),16);
        for(WebElement webElement: searchResults){
            System.out.println(webElement.getText());
        }
    }
    //@Test
    public void TC_03_Custom_Dropdown_JQuery(){
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInDropdown( "span#speed-button","ul#speed-menu>li>div", "Fast" );
        selectItemInDropdown( "span#files-button","ul#files-menu>li>div", "ui.jQuery.js" );
        selectItemInDropdown( "span#number-button","ul#number-menu>li>div", "10" );
        selectItemInDropdown( "span#salutation-button","ul#salutation-menu>li>div", "Mr." );
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Fast");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button span.ui-selectmenu-text")).getText(),"ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),"10");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),"Mr.");
    }
    //@Test
    public void TC_04_Custom_Dropdown_React(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("div.dropdown","div.item>span","Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Christian");
        selectItemInDropdown("div.dropdown","div.item>span","Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Justen Kitsune");
    }
    //@Test
    public void TC_04_Custom_Dropdown_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu>li>a","Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");
        selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu>li>a","First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");
    }
    //@Test
    public void TC_04_Custom_Dropdown_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        enterItemInDropdown("input.search","div.visible.menu.transition>div","Argentina");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Argentina");
        enterItemInDropdown("input.search","div.visible.menu.transition>div","Belgium");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Belgium");
    }
    @Test
    public void TC_05_Custom_Dropdown_Editable() {
        driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
        driver.findElement(By.cssSelector("button.btn.btn-primary.x")).click();
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("button#selectize-input")));
        selectItemInDropdown("button#selectize-input","div.dropdown-menu.show>a","HR-V G (Đỏ cá tính/ trắng ngọc quý phái)");
        Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(),"HR-V G (Đỏ cá tính/ trắng ngọc quý phái)");

        Select selectProvince = new Select(driver.findElement(By.cssSelector("select#province")));
        selectProvince.selectByVisibleText("TP. Hồ Chí Minh");
        Assert.assertEquals(selectProvince.getFirstSelectedOption().getText(),"TP. Hồ Chí Minh");

        Select selectRegistrationFee = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
        selectRegistrationFee.selectByVisibleText("Khu vực I");
        Assert.assertEquals(selectRegistrationFee.getFirstSelectedOption().getText(),"Khu vực I");
    }

    @Test
    public void TC_06_Multiple_Selection_Dropdown() {
        driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
        String[] expectedValueItem= {"May","June","January","December"};
        selectMultiItemInDropdown("div.multiple-select button.ms-choice","div.ms-drop.bottom>ul>li", expectedValueItem);
        isItemSelected(expectedValueItem);
    }
    public void selectItemInDropdown(String parentLocator, String childLocator, String expectedItem) {
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentLocator)));
        driver.findElement(By.cssSelector(parentLocator)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
        for (WebElement item:allItems){
            System.out.println(item.getText());
            if (item.getText().trim().equals(expectedItem)) {
                sleepInSecond(1);
                item.click();
                break;
            }
        }
    }

    public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
        // 1: click vào cái dropdown cho nó xổ hết tất cả các giá trị ra
        driver.findElement(By.cssSelector(parentXpath)).click();

        // 2: chờ cho tất cả các giá trị trong dropdown được load ra thành công
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childXpath)));

        List<WebElement> allItems = driver.findElements(By.cssSelector(childXpath));

        // Duyệt qa hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
        for (WebElement childElement : allItems) {
            // "January", "April", "July"
            for (String item : expectedValueItem) {
                if (childElement.getText().equals(item)) {
                    // 3: scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì ko cần scroll)
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
                    sleepInSecond(1);

                    // 4: click vào item cần chọn
                    childElement.click();
                    sleepInSecond(1);

                    List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
                    System.out.println("Item selected = " + itemSelected.size());
                    if (expectedValueItem.length == itemSelected.size()) {
                        break;
                    }
                }
            }
        }
    }
    public boolean isItemSelected(String[] months) {
        List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
        int numberItemSelected = itemSelected.size();

        String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
        System.out.println("Text da chon = " + allItemSelectedText);

        if (numberItemSelected <= 3 && numberItemSelected > 0) {
            boolean status = true;
            for (String item : months) {
                if (!allItemSelectedText.contains(item)) {
                    status = false;
                    return status;
                }
            }
            return status;
        } else if (numberItemSelected >= 12) {
            return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
        } else if (numberItemSelected > 3 && numberItemSelected < 12) {
            return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
        } else {
            return false;
        }
    }
    public void enterItemInDropdown(String parentLocator, String childLocator, String expectedItem) {
        WebElement dropdownTextbox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parentLocator)));
        dropdownTextbox.clear();
        dropdownTextbox.sendKeys(expectedItem);
        sleepInSecond(3);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
        for (WebElement item:allItems){
            System.out.println(item.getText());
            if (item.getText().trim().equals(expectedItem)) {
                sleepInSecond(1);
                item.click();
                break;
            }
        }
    }
    @AfterClass
    public void cleanBrowser(){
        //driver.quit();
    }
    protected void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
