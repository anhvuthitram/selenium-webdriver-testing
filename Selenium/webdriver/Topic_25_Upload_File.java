package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_25_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String hcmName = "HoChiMinh.jpg";
    String hnName = "HaNoi.jpg";
    String dnName ="DaNang.jpg";

    String hcmFilePath = projectPath + File.separator+"uploadFiles"+ File.separator+hcmName ;
    String hnFilePath = projectPath + File.separator+"uploadFiles"+ File.separator+hnName ;
    String dnFilePath = projectPath + File.separator+"uploadFiles"+ File.separator+dnName ;

    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void TC_01_Single_File(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        //WebElement uploadFileElement = driver.findElement(By.cssSelector("input[name='files[]']"));
        By uploadBy = By.cssSelector("input[name='files[]']");
        // nếu dùng uploadFileElement thì sẽ bị lỗi vì 1 element đã được khai báo trước đó và bị load lại nhưng vẫn mang ra sử dụng => Stale element exception
        driver.findElement(uploadBy).sendKeys(hcmFilePath);
        sleepInSecond(2);

        driver.findElement(uploadBy).sendKeys(hnFilePath);
        sleepInSecond(2);

        driver.findElement(uploadBy).sendKeys(dnFilePath);
        sleepInSecond(2);

        //verify file dc load len thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+hcmName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+hnName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+dnName+"']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));

        for (int i = 0; i < startButtons.size(); i++) {
            if (startButtons.get(i).isDisplayed()) {
                startButtons.get(i).click();
                sleepInSecond(3);
            }
        }
        // kiem tra file dc upload thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+hcmName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+hnName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+dnName+"']")).isDisplayed());
    }

    @Test
    public void TC_02_Multiple_File(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadBy = By.cssSelector("input[name='files[]']");
        driver.findElement(uploadBy).sendKeys(hcmFilePath+"\n"+hnFilePath+"\n"+dnFilePath);
        sleepInSecond(3);

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+hcmName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+hnName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+dnName+"']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));

        for (int i = 0; i < startButtons.size(); i++) {
            if (startButtons.get(i).isDisplayed()) {
                startButtons.get(i).click();
                sleepInSecond(3);
            }
        }
        // kiem tra file dc upload thanh cong
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+hcmName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+hnName+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+dnName+"']")).isDisplayed());
    }
    @AfterClass
    public void cleanBrowser(){

        //driver.quit();
    }
    protected void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
