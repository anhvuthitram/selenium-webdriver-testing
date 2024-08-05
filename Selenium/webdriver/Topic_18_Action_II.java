package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_18_Action_II {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void TC_04_Right_Click() throws InterruptedException {
        //https://docs.google.com/document/d/15MqNX4HLiR29Vn2XhFhugTb2AJpAT16tiEGHzQ0GeFo/edit#
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        Actions action = new Actions(driver);
        By quitContextBy =By.cssSelector("li.context-menu-icon-quit");
        // right click
        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(quitContextBy).isDisplayed());
        //hover mouse
        action.moveToElement(driver.findElement(quitContextBy)).perform();
        Thread.sleep(3000);
        //setTimeout(() => {debugger;}, 3000 ); in Console to get element in FF
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-hover.context-menu-visible")).isDisplayed());
        //click Quit and OK
        action.click(driver.findElement(quitContextBy)).perform();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Assert.assertFalse(driver.findElement(quitContextBy).isDisplayed());
        Thread.sleep(3000);
    }


    @AfterClass
    public void cleanBrowser(){
        //driver.quit();
    }


}
