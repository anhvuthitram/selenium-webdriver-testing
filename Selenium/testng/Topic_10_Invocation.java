package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_10_Invocation {

    WebDriver driver;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test(invocationCount = 5)
    public void TC_01_() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        String firstName = "Anh", lastName="Vu", emailAddress = getEmailAddress();
        String companyName="Selenium Webdriver", password ="123456";
        String day="15",month="November",year="1988";

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
        // Day dropdown
        Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
        // chọn ngày
        dayDropdown.selectByVisibleText(day);
        // Verify dropdown này là single
        Assert.assertFalse(dayDropdown.isMultiple());
        // Verify số lượng item trong dropdown là 32
        Assert.assertEquals(dayDropdown.getOptions().size(),32);

        new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
        new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        driver.findElement(By.id("register-button")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");
        driver.findElement(By.cssSelector("a.ico-logout")).click();
        System.out.println(emailAddress);

    }
    public String getEmailAddress(){
        Random rand = new Random();
        return "anhvu" + rand.nextInt(99999)+"@gmail.com";
    }

}
