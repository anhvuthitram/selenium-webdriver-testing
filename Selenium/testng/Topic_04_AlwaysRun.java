package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_AlwaysRun {
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Run Before Class");
        // nếu bị failed ở BeforeClass/AfterClass thì các testcase sẽ bị skip
        Assert.assertTrue(false);
    }
    @Test
    public void TC_01_()
    {
        System.out.println("Run testcase 01");
    }
    @Test
    public void TC_02_()
    {
        System.out.println("Run testcase 02");
    }
    @AfterClass(alwaysRun = true)
    public void afterClass()
    {
        System.out.println("Run After Class");
    }

}
