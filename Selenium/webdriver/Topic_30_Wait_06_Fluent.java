package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_30_Wait_06_Fluent {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;

    FluentWait<String> fluentString;


    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        // tổng thời gian là 10s,
        // Thời gian để lặp lại tìm element default (polling time) 0.5s
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // tổng thời gian là 10s,
        // Thời gian để lặp lại tìm element tự set là (polling time) 0.5s
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
        fluentDriver = new FluentWait<WebDriver>(driver);

    }

    public void TC_01_(){
        // khởi tạo
        fluentDriver = new FluentWait<WebDriver>(driver);
        fluentElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector("")));
        fluentString = new FluentWait<String>("Hello World!");
        // Setting
        // Tổng time
        fluentDriver.withTimeout(Duration.ofSeconds(10));
        // Polling time
        fluentDriver.pollingEvery(Duration.ofMillis(300));
        // Ignore NoSuchElement
        fluentDriver.ignoring(NoSuchElementException.class);
        // Ignore TimeoutException
        fluentDriver.ignoring(TimeoutException.class);
        // Condition
        fluentDriver.until(new Function<WebDriver,String>() {

            @Override
            public String apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("")).getText();
            }
        });

        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class, TimeoutException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webDriver.findElement(By.cssSelector("")).isDisplayed();
                    }
                });

    }
    @Test
    public void TC_01_FluentWait(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start button")).click();
        // Chờ Hello World xuất hiện trong 10s
        // Setting
        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
               .ignoring(NoSuchElementException.class);
        // condition
//        fluentDriver.until(new Function<WebDriver, Boolean>() {
//                    @Override
//                    public Boolean apply(WebDriver webDriver) {
//                        return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
//                    }
//                });
        String helloText = fluentDriver.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                return driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
            }
        });

//        String helloText = fluentDriver.until(new Function<WebDriver, String>() {
//            @Override
//            public String apply(WebDriver webDriver) {
//                String text =  driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
//                System.out.println(text);
//                return text;
//            }
//        });
        Assert.assertEquals(helloText,"Hello World!");
    }
    @Test
    public void TC_02_FluentWait(){
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement timeElement = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
        fluentElement = new FluentWait<WebElement>(timeElement);
        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                String text = webElement.getText();
                System.out.println(text);
                return text.endsWith("00");
            }
        });
    }
    @Test
    public void TC_03_FluentWait_Using_waitAndFindElement(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        waitAndFindElement(By.cssSelector("div#start button")).click();
        Assert.assertEquals(waitAndFindElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
    }

    @AfterClass
    public void cleanBrowser(){
       // driver.quit();
    }
    public WebElement waitAndFindElement(By locator){
        FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver);
        fluentDriver.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);

        return fluentDriver.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
    }
}
