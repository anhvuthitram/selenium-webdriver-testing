package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Topic_10_Textbox_TextArea {
    WebDriver driver;
    public void initialBrowser(){

    }
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/Login");
    }
    @Test
    public void TC_01_Register(){

        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("a.skip-account")).click();
        driver.findElement(By.cssSelector("div#header-account a[title='My Account'] ")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

    }
    @Test
    public void TC_02_Login(){

    }
    @AfterClass
    public void cleanBrowser(){

        driver.quit();
    }




}
