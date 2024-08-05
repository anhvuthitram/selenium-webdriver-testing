package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Wait_01_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    By reconfirmEmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");
    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("https://www.facebook.com");

    }
    @Test
    public void TC_01_Visible(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("anh.vu@aaa.com");
        sleepInSecond(2);

        // Điều kiện 1: Element có xuất hiện trên UI và có trong cây HTML
        // tai dung thoi diem nay textbox re-confirm email visible/displayed
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));

        // kiem tra element dang hien thi
        Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }
    @Test
    public void TC_02_Invisible_In_DOM(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSecond(2);

        // Điều kiện 2- Element ko xuất hiện trên UI và vẫn có trên cây HTML
        // tai dung thoi diem nay textbox re-confirm email invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));
        // kiem tra element dang khong hien thi
        Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }
    @Test
    public void TC_02_Invisible_Not_In_DOM(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img")).click();
        sleepInSecond(3);
        // Điều kiện 3 - Element không xuất hiện trên UI và cũng ko có trên cây HTML.
        // tai dung thoi diem nay textbox re-confirm email invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));
        // kiem tra element dang khong hien thi
        // chạy rất lâu, vang ra exception -> failed
       // Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }
    @Test
    public void TC_03_Presence(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("anh.vu@aaa.com");
        sleepInSecond(2);

        // Điều kiện 1: Element có xuất hiện trên UI và có trong cây HTML
        // tai dung thoi diem nay textbox re-confirm email presence(có trong HTML)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSecond(2);

        // Điều kiện 2- Element ko xuất hiện trên UI và vẫn có trên cây HTML
        // tai dung thoi diem nay textbox re-confirm email presence(có trong HTML)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));
    }
    @Test
    public void TC_03_Staleness(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSecond(2);
        // Tại thời điểm này element xuất hiện và sẽ findElement dc
        // attach to the DOM
        WebElement reconfirmEmail = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__"));
        // close popup
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img")).click();
        sleepInSecond(3);
        //Điều kiện 3 - Element không xuất hiện trên UI và cũng ko có trên cây HTML.
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));
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
