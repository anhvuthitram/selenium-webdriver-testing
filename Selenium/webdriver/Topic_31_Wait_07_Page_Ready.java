package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_31_Wait_07_Page_Ready {
    WebDriver driver;
    WebDriverWait explicitWait;

    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void TC_01_Admin_NopCommerce_Pay_Ready(){
        driver.get("https://admin-demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Password")).clear();

        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSecond(2);

        //div#ajaxBusy
        Assert.assertTrue(isPageLoadedSuccess());

        driver.findElement(By.xpath("//i[contains(@class,'fa-user')]/following-sibling::p[contains(string(),'Customers')]")).click();
        driver.findElement(By.xpath("//ul[contains(@style,'display')]//p[contains(string(),'Customers')]")).click();
        Assert.assertTrue(isPageLoadedSuccess());

        driver.findElement(By.xpath("//i[contains(@class,'fa-book')]/following-sibling::p[contains(string(),'Catalog')]")).click();
        driver.findElement(By.xpath("//ul[contains(@style,'display')]//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Products')]")).click();
        Assert.assertTrue(isPageLoadedSuccess());

        driver.findElement(By.xpath("//i[contains(@class,'fa-tags')]/following-sibling::p[contains(string(),'Promotions')]")).click();
        driver.findElement(By.xpath("//ul[contains(@style,'display')]//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Newsletter subscribers')]")).click();
        Assert.assertTrue(isPageLoadedSuccess());
    }
    @Test
    public void TC_01_Admin_NopCommerce_Ajax_Loading(){
        driver.get("https://admin-demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Password")).clear();

        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();
         sleepInSecond(2);
        //div#ajaxBusy
        Assert.assertTrue(waitAjaxLoadingInvisible());

        driver.findElement(By.xpath("//i[contains(@class,'fa-user')]/following-sibling::p[contains(string(),'Customers')]")).click();
        driver.findElement(By.xpath("//ul[contains(@style,'display')]//p[contains(string(),'Customers')]")).click();
        Assert.assertTrue(waitAjaxLoadingInvisible());
        sleepInSecond(2);

        driver.findElement(By.xpath("//i[contains(@class,'fa-book')]/following-sibling::p[contains(string(),'Catalog')]")).click();
        driver.findElement(By.xpath("//ul[contains(@style,'display')]//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Products')]")).click();
        Assert.assertTrue(waitAjaxLoadingInvisible());
    }
    @Test
    public void TC_02_OrangeHRM_API_Document(){
        driver.get("https://api.orangehrm.com/");
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner"))));
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")).isDisplayed());
    }
    @AfterClass
    public void cleanBrowser(){

       // driver.quit();
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        ExpectedCondition<Boolean> ajaxLoading = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad) && explicitWait.until(ajaxLoading);
    }
    public boolean waitAjaxLoadingInvisible(){
        return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
    }
    protected void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
