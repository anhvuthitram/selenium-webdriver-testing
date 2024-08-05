package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_22_Windows_Tab {
    //https://docs.google.com/document/d/1IZn3_cDV95SRT7RuEl8NWjxw4IPmjwMpd38-HmXfgYM/edit
    WebDriver driver;
    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void TC_01_Basic_Form() throws InterruptedException {

        driver.get("https://automationfc.github.io/basic-form/index.html");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        //lay ra ID của tab hiện tại
        String parentID = driver.getWindowHandle();
        System.out.println("Parent tab ID =" +parentID);

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Google");

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
        Thread.sleep(3000);

        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Facebook – log in or sign up");

        driver.findElement(By.cssSelector("button[name='login']")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Selenium WebDriver");
    }
    @Test
    public void TC_02_Live_TechPanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//span[text()='Compare']")).click();

        switchToWindowByTitle("Products Comparison List - Magento Commerce");

        //Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "COMPARE PRODUCTS");

        driver.close();

        switchToWindowByTitle("Mobile");

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        Thread.sleep(3000);
        Alert alert= driver.switchTo().alert();
        alert.accept();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The comparison list was cleared.");
    }
@Test
    public void TC_03_Cambridge_Dictionary() {
        String sendString = "automation";
        driver.get("https://dictionary.cambridge.org/vi/");
        driver.findElement(By.xpath("//span[text()='Đăng nhập']")).click();
        switchToWindowByTitle("Login");
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class ='gigya-composite-control gigya-composite-control-textbox']/span[text()]")).getText(),"This field is required");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class ='gigya-composite-control gigya-composite-control-password']/span[text()]")).getText(),"This field is required");
        driver.close();
        switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        driver.findElement(By.cssSelector("input#searchword")).sendKeys(sendString);
        driver.findElement(By.cssSelector("button.cdo-search-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span.hw.dhw")).getText(),sendString);
    }
    @Test
    public void TC_04_Selenium_4x(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        System.out.println("Page ID =" + driver.getWindowHandle());
        System.out.println("Page Title =" + driver.getTitle());
        System.out.println("Page URL =" + driver.getCurrentUrl());

        driver.switchTo().newWindow(WindowType.TAB).get("http://live.techpanda.org/index.php/customer/account/");
        sleepInSecond(3);
        System.out.println("Page ID =" + driver.getWindowHandle());
        System.out.println("Page Title =" + driver.getTitle());
        System.out.println("Page URL =" + driver.getCurrentUrl());
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");

        switchToWindowByTitle("Mobile");
        System.out.println("Page ID =" + driver.getWindowHandle());
        System.out.println("Page Title =" + driver.getTitle());
        System.out.println("Page URL =" + driver.getCurrentUrl());

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//span[text()='Compare']")).click();

        switchToWindowByTitle("Products Comparison List - Magento Commerce");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "COMPARE PRODUCTS");

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
    public void switchToWindowByID(String expectedID){
        Set<String> allIDs = driver.getWindowHandles();
           for (String id : allIDs)
            //nếu ID nào khác với parentID thì switch vào
            if (!id.equals(expectedID))
            {
                driver.switchTo().window(id);
                break;
            }
    }
    public void switchToWindowByTitle(String expectedTitle){
        Set<String> allIDs = driver.getWindowHandles();
        for (String id:allIDs){
            driver.switchTo().window(id);
            //lay ra title cua tab hien tai
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(expectedTitle)){
                break;
            }
        }
    }
    public void closeAllWindowWithoutParent(String parentID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id:allIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
    }

}
