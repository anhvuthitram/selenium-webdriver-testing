package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Frame_Iframe {
    WebDriver driver;
    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void TC_01_Iframe_FormSite() throws InterruptedException {
        // trang HTML A
        driver.get("https://www.formsite.com/templates/industry/campus-safety-survey/");
        driver.findElement(By.cssSelector("img[alt='Campus Safety Survey']")).click();
        // switch to iframe
        //driver.switchTo().frame(0);
        //driver.switchTo().frame("");
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));
        // trang HTML B
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("South Dorm");
        driver.findElement(By.cssSelector("input#RESULT_RadioButton-4_0")).isSelected();
        Thread.sleep(3000);
        // Tu B quay lai A
        driver.switchTo().defaultContent();

        //driver đa quay lai A rồi
        driver.findElement(By.cssSelector("a.menu-item-login.fs-btn--transparent-kashmir")).click();
        driver.findElement(By.cssSelector("button#login")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");
    }
    @Test
    public void TC_02_Iframe_Toidicodedao(){
        driver.get("https://toidicodedao.com/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb_iframe_widget iframe")));
        // trang iframe co link FB
       // driver.findElement(By.cssSelector("a[title='Tôi đi code dạo']")).click();
        // driver vao trang FB lay so followers
        By followersText = By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div[text()]");
        Assert.assertEquals(driver.findElement(followersText).getText(),"406,625 followers");
    }
    @AfterClass
    public void cleanBrowser(){

        driver.quit();
    }
}
