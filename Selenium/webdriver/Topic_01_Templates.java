package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_Templates {
    WebDriver driver;
    public void initialBrowser(){

    }
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com");
    }
    public void TC_01_Register(){

    }
    public void TC_02_Login(){

    }
    public void cleanBrowser(){
        driver.quit();
    }



}
