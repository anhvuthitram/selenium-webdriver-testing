package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.constant.ConstantDescs.NULL;

public class Topic_12_Parellel_Method {

    WebDriver driver;


    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
        Thread.sleep(5000);
    }
    @Test
    public void TC_01_() {
        System.out.println("Run TC01");
    }
    @Test
    public void TC_02_() {
        System.out.println("Run TC02");
    }
    @Test
    public void TC_03_() {
        System.out.println("Run TC03");
    }
    @Test
    public void TC_04_() {
        System.out.println("Run TC04");
    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        if (driver != NULL) driver.close();
    }

}
