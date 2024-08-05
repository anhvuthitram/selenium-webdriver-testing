package webdriver;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_23_New_Driver {
    WebDriver userDriver;
    WebDriver adminDriver;
    String firstName = "Anh", lastName="Vu", emailAddress = getEmailAddress();
    String companyName="Selenium Webdriver", password ="123456";
    String day="15",month="November",year="1988";

    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        userDriver = new FirefoxDriver();
        userDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        adminDriver = new ChromeDriver();
        adminDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void TC_01_() throws InterruptedException {
        userDriver.get("https://demo.nopcommerce.com/");
        userDriver.findElement(By.cssSelector("a.ico-register")).click();

        userDriver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        userDriver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
        // Day dropdown
        Select dayDropdown = new Select(userDriver.findElement(By.name("DateOfBirthDay")));
        // chọn ngày
        dayDropdown.selectByVisibleText(day);
        // Verify dropdown này là single
        Assert.assertFalse(dayDropdown.isMultiple());
        // Verify số lượng item trong dropdown là 32
        Assert.assertEquals(dayDropdown.getOptions().size(),32);

        new Select(userDriver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
        new Select(userDriver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

        userDriver.findElement(By.id("Email")).sendKeys(emailAddress);
        userDriver.findElement(By.id("Password")).sendKeys(password);
        userDriver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        userDriver.findElement(By.id("register-button")).click();
        Thread.sleep(3000);

        Assert.assertEquals(userDriver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");


        adminDriver.get("https://admin-demo.nopcommerce.com");
        adminDriver.findElement(By.id("Email")).clear();
        adminDriver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");
        adminDriver.findElement(By.id("Password")).clear();
        adminDriver.findElement(By.id("Password")).sendKeys("admin");
        adminDriver.findElement(By.cssSelector("button.login-button")).click();
        Thread.sleep(3000);

    }
    @Test
    public void TC_02_Login(){

    }
    @AfterClass
    public void cleanBrowser(){

        //driver.quit();
    }

    public String getEmailAddress(){
        Random rand = new Random();
        return "anhvu" + rand.nextInt(99999)+"@gmail.com";
    }




}
