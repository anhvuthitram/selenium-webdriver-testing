package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.Logs;
import org.testng.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_06_Run_On_Browser_Commands {

    WebDriver driver;
    @BeforeClass
    public void initialBrowser() throws MalformedURLException {
        // Run with browser(local)
        driver = new FirefoxDriver();
        driver = new EdgeDriver();
        driver = new ChromeDriver();
        driver = new SafariDriver();
        driver = new EdgeDriver();
        driver = new InternetExplorerDriver();
        driver = new ChromeDriver();

        // Remote(Grid/Docker/Cloud Testing)
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("https://localhost:4444"), chromeOptions);

    }
    @Test
    public void TC_01(){
        // mo ra url bất kỳ(phải bắt đầu là http hoặc https)
        driver.get("https://demo.nopcommerce.com/");
        // đóng cái nào mà driver đang đứng(active tab/window)
        driver.close();
        // đóng tất cả các tab cũng như cửa sổ
        driver.quit();
        // lấy ra title của page hiện tại
        // 1. lưu lại rồi verify
        String homePageTitle= driver.getTitle();
        Assert.assertEquals(homePageTitle, "nopCommerce demo store");

        // 2. kiểm tra trực tiếp
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");

        // lấy source code của page
        Assert.assertTrue(driver.getPageSource().contains("Condition of Use"));

        // Lấy ra ID của tab/window đang active
        driver.getWindowHandle();

        // Lấy ra tất cả ID cùa tất cả các tab/window đang có
        driver.getWindowHandles();

        // đi tìm element
        driver.findElement(By.xpath(""));

        // đi tìm element
        driver.findElements(By.xpath(""));

        // selenium v3
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

        // selenium v4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));

        // lay log
        Logs log= driver.manage().logs();
        LogEntries logEntries =log.get("BROWSER");
        //for (logEntry logEn:  logEntries){
        //    System.out.println(logEn);
        //}

        //
        WebDriver.Navigation navigation =driver.navigate();

        // refresh
        navigation.refresh();

        // quay lại trang trước đó
        navigation.back();

        //chuyển tiếp tới trang trước đó
        navigation.forward();

        // mở url bát kỳ
        navigation.to("https://demo.nopcommerce.com/");

        // Alert / Iframe/ Windows(tab)
        WebDriver.TargetLocator targetLocator= driver.switchTo();
        targetLocator.alert().accept();
        targetLocator.alert().dismiss();

        // handle frame hoac Ifarme
        targetLocator.frame(""); //*

        //Window
        targetLocator.window("");//**


    }
}
