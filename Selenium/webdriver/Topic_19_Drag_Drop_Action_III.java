package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;

public class Topic_19_Drag_Drop_Action_III {
    WebDriver driver;
    Actions action;
    String projectPath= System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;
    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        action = new Actions(driver);
        jsExecutor= (JavascriptExecutor) driver;
    }
    @Test
    public void TC_01_Drag_Drop_HTML4() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement sourceCircle = driver.findElement(By.id("draggable"));
        WebElement targetCircle =driver.findElement(By.id("droptarget"));
        action.dragAndDrop(sourceCircle,targetCircle).perform();
        Thread.sleep(6000);
        Assert.assertEquals(driver.findElement(By.id("droptarget")).getText(),"You did great!");
        Assert.assertEquals(Color.fromString(driver.findElement(By.id("droptarget")).getCssValue("background-color")).asHex(),"#03a9f4");
    }
    @Test
    public void TC_02_Drag_Drop_HTML5() throws InterruptedException {
        // TC này failed trên FF nhưng pass tren Chrome do chay tren HTML5
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        WebElement sourceColumn = driver.findElement(By.id("column-a"));
        WebElement targetColumn =driver.findElement(By.id("column-b"));
        action.dragAndDrop(sourceColumn,targetColumn).perform();
        Thread.sleep(6000);
        Assert.assertEquals(driver.findElement(By.id("column-b")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.id("column-a")).getText(),"B");
    }
    @Test
    public void TC_03_Drag_Drop_HTML5() throws InterruptedException, IOException {
        // TC này failed trên FF nhưng pass tren Chrome do chay tren HTML5
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        String jqueryDragDropContent = getContentFile(projectPath+"\\dragDrop\\dragAndDrop.js");
        // Drag A-> B
        jsExecutor.executeScript(jqueryDragDropContent);
        Assert.assertEquals(driver.findElement(By.id("column-b")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.id("column-a")).getText(),"B");
        // Drag B-> A
        jsExecutor.executeScript(jqueryDragDropContent);
        Assert.assertEquals(driver.findElement(By.id("column-b")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.id("column-a")).getText(),"A");

    }
    public String getContentFile(String filePath) throws IOException {
            Charset cs = Charset.forName("UTF-8");
            FileInputStream stream = new FileInputStream(filePath);
            try {
                Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
                StringBuilder builder = new StringBuilder();
                char[] buffer = new char[8192];
                int read;
                while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                    builder.append(buffer, 0, read);
                }
                return builder.toString();
            } finally {
                stream.close();
            }
    }
    @Test
    public void TC_03_Drag_Drop_Java_Robot() throws AWTException, InterruptedException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        // luc chay duoc luc khong => khong on dinh, nen test manual ko dung auto
        // Drag A-> B
        dragAndDropHTML5ByXpath("div#column-a","div#column-b");
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.id("column-b")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.id("column-a")).getText(),"B");
        // Drag B-> A
        dragAndDropHTML5ByXpath("div#column-a","div#column-b");
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.id("column-b")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.id("column-a")).getText(),"A");
    }
    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.cssSelector(sourceLocator));
        WebElement target = driver.findElement(By.cssSelector(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        org.openqa.selenium.Dimension sourceSize = source.getSize();
        org.openqa.selenium.Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        org.openqa.selenium.Point sourceLocation = source.getLocation();
        org.openqa.selenium.Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    @Test
    public void TC_04_Scroll_To_Element() throws InterruptedException {
        driver.get("http://live.techpanda.org/index.php/about-magento-demo-store/");
        action.scrollToElement(driver.findElement(By.cssSelector("input#newsletter"))).perform();
        Thread.sleep(3000);
    }

        @AfterClass
    public void cleanBrowser(){

       // driver.quit();
    }




}
