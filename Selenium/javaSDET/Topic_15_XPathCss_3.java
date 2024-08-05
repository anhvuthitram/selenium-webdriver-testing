package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_XPathCss_3 {
    WebDriver driver;

    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
@Test
    public void Topic_XPath_Startswith_Contains(){
    driver = new FirefoxDriver();
    driver.get("https://member.lazada.vn/user/login");

        driver.findElement(By.xpath("//input[contains(@data-spm-anchor-id,'a2o42.login_signup')]"));
        driver.findElement(By.xpath("//input[starts-with(@data-spm-anchor-id,'a2o42.login_signup')]"));

    }


}
