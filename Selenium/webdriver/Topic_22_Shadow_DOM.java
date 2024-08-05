package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_22_Shadow_DOM {
    WebDriver driver;
    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }
    @Test
    public void TC_01_ShadowDOM(){
        //driver đại diện cho cái real DOM bên ngoài
        driver.get("https://automationfc.github.io/shadow-dom/");

        // shadowRootContext đại diện cho cái shadow DOM bên trong
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

        String someText = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);
        // shadow ko support Xpath???
        List<WebElement> inputAll = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println(inputAll.size());

        WebElement nestedShadowHost= shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = nestedShadowHost.getShadowRoot();

        String nestedText = nestedShadowRootContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(nestedText);
    }
    @Test
    public void TC_02_ShadowDOM() throws InterruptedException {
        driver.get("https://shopee.vn");

        WebElement shadowHostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowRootContext= shadowHostElement.getShadowRoot();
        // verify popup hien thi
        if(shadowRootContext.findElements(By.cssSelector("div.home-popup__content")).size()>0
                && shadowRootContext.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed())
        {
            //close popup
            shadowRootContext.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
            Thread.sleep(3000);
        }
        // ko hiển thị /đã bị đóng qua step search dữ liệu
        driver.findElement(By.cssSelector("input.shopee-searchba")).sendKeys("IPhone 15 ProMax");
        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
        Thread.sleep(3000);
    }
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }




}
