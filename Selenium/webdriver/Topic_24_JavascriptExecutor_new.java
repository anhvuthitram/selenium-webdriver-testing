package webdriver;

import org.openqa.selenium.JavascriptExecutor;
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

public class Topic_24_JavascriptExecutor_new {
    WebDriver driver;
    JavascriptExecutor jsExecutor;


    WebDriverWait webdriverWait;
    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }
    @Test
    public void TC_00_Example(){
        driver.get("https://demo.nopcommerce.com/");
        // ko nen dung js de get element vi no ko giong hanh vi cua user
        WebElement textbookJS = (WebElement) jsExecutor.executeScript("return document.querySelector('input#small-searchterms')");
        textbookJS.sendKeys("automation FC");

    }
    @Test
    public void TC_01_Login(){
        jsExecutor.executeScript("window.location ='http://live.techpanda.org/'");
        webdriverWait.until(ExpectedConditions.urlToBe("http://live.techpanda.org/"));

        String domainText = (String)jsExecutor.executeScript("return document.domain");
        Assert.assertEquals(domainText,"live.techpanda.org");

        String urlText = (String)jsExecutor.executeScript("return document.URL");
        Assert.assertEquals(domainText,"http://live.techpanda.org/");

       // (WebElement) jsExecutor.executeScript("return document.querySelector('input#small-searchterms')");

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
