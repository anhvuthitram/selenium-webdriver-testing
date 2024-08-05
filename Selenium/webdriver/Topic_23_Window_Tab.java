package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_23_Window_Tab {
    WebDriver driver;
    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void TC_01_switchByID(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // lấy ra ID của tab/window đang active tại page đó
        System.out.println("Window ID = "+driver.toString());
        String gitHubWindowID = driver.getWindowHandle();
        System.out.println("Page ID =" + gitHubWindowID);
        System.out.println("Page Title =" + driver.getTitle());
        System.out.println("Page URL =" + driver.getCurrentUrl());

        // click vo Google link -> tự dong bat len tab moi
        // nhừng với code của selenium la driver ko tự nhảy qua - nó vẫn ở tab cũ
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(3);

        // switch to google tab
        switchToWindowByID(gitHubWindowID);
        String googleID = driver.getWindowHandle();

        sleepInSecond(3);
        System.out.println("Page ID =" + googleID);
        System.out.println("Page Title =" + driver.getTitle());
        System.out.println("Page URL =" + driver.getCurrentUrl());

        // switch to github tab
        switchToWindowByID(googleID);
        sleepInSecond(3);
        System.out.println("Page ID =" + gitHubWindowID);
        System.out.println("Page Title =" + driver.getTitle());
        System.out.println("Page URL =" + driver.getCurrentUrl());

        // click vô facebook
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(3);



        closeAllWindowWithoutParent(gitHubWindowID);
    }

    @Test
    public void TC_01_switchByTitle() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // lấy ra ID của tab/window đang active tại page đó
        System.out.println("Window ID = "+driver.toString());
        String gitHubWindowID = driver.getWindowHandle();
        System.out.println("Page ID =" + gitHubWindowID);
        System.out.println("Page Title =" + driver.getTitle());
        System.out.println("Page URL =" + driver.getCurrentUrl());

        // click vo Google link -> tự dong bat len tab moi
        // nhừng với code của selenium la driver ko tự nhảy qua - nó vẫn ở tab cũ
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(3);

        // switch to google tab
        switchToWindowByTitle("Google");
        String googleID = driver.getWindowHandle();
        System.out.println("Page ID =" + googleID);
        System.out.println("Page Title =" + driver.getTitle());
        System.out.println("Page URL =" + driver.getCurrentUrl());

        // switch back to github
        switchToWindowByTitle("Selenium WebDriver");
        String gitHubWindowID_new = driver.getWindowHandle();
        System.out.println("Page ID =" + gitHubWindowID_new);
        System.out.println("Page Title =" + driver.getTitle());
        System.out.println("Page URL =" + driver.getCurrentUrl());

        // click vo Facebook -> tự dong bat len tab moi
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(3);
        // switch to Facebook tab
        switchToWindowByTitle("Facebook – log in or sign up");
        String facebookID = driver.getWindowHandle();
        System.out.println("Page ID =" + facebookID);
        System.out.println("Page Title =" + driver.getTitle());
        System.out.println("Page URL =" + driver.getCurrentUrl());

        closeAllWindowWithoutParent(gitHubWindowID);

    }
        @Test
    public void TC_02_Login(){

    }
    @AfterClass
    public void cleanBrowser(){

       // driver.quit();
    }
    protected void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void switchToWindowByID(String expectedID) {
        // Set dc lưu không trùng nhau, nhưng List thì có thể dc
        Set<String> allWindowIDs = driver.getWindowHandles();
        for(String id:allWindowIDs) {
            if (!id.equals(expectedID)){
                driver.switchTo().window(id);
            }
        }
    }
    public void switchToWindowByTitle(String expectedTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for( String id:allWindowIDs) {
            driver.switchTo().window(id);
            String windowTitle = driver.getTitle();
            if (windowTitle.equals(expectedTitle)){
                break;
            }
        }
    }
    public void closeAllWindowWithoutParent(String parentID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id:allIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
    }


}
