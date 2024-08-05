package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_08_WebBrowserElement {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_getCurrentUrl() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        //driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul/li/a[@title='My Account']")).click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://live.techpanda.org/index.php/customer/account/login/");

    }
    @Test
    public void TC_02_getTitle() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.block-title~ul>li>a[title='My Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Customer Login");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }
    @Test
    public void TC03_navigateFunction(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.block-title~ul>li>a[title='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }
    @Test
    public void TC04_getPageSourceCode(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.block-title~ul>li>a[title='My Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }


}
