package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_29_Wait_05_Mix_Implicit_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;
    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
    }
    @Test
    public void TC_01_Mix_Implicit_Explicit(){
        driver.get("https://www.facebook.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println("Start time: "+getDateTimeNow());
        try {
            WebElement until = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
        } catch (Exception e) {
            System.out.println("End time: " + getDateTimeNow());
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }
    @Test
    public void TC_02_Mix_Implicit_Explicit(){
        driver.get("https://www.facebook.com/");
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println("Start time: "+getDateTimeNow());
        try {
            WebElement until = explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#automation"))));
        } catch (Exception e) {
            System.out.println("End time: " + getDateTimeNow());
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }
    @Test
    public void TC_02_Login(){

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
    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }



}
