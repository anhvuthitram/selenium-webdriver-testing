package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

//common function https://gist.github.com/daominhdam/e639d2d2569e27b43c7a9eac326e2737
// https://docs.google.com/document/d/1tjHswRo2ovMrT20YCVXEfidTk0yK9OcR67BQ5kG7I3s/edit

public class Topic_24_JavascriptExecutor {
    //WebDriver driver;
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }
    @Test
    public void TC_01_jsExecutor() throws InterruptedException {
        // truy cập vào trang live techpanda
        executeForBrowser("window.location='http://live.techpanda.org/'");
        sleepInSecond(3);
        //get domain cua page
        String domainName = (String) executeForBrowser ("return document.domain;");
        Assert.assertEquals(domainName,"live.techpanda.org");
        // get URL cua page
        String URL = (String) executeForBrowser("return document.URL;");
        Assert.assertEquals(URL,"http://live.techpanda.org/");
        //Open Mobile page
        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        hightlightElement("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");

        String title = (String) executeForBrowser("return document.title;");
        Assert.assertEquals(title,"Customer Service");

        scrollToElementOnDown("//input[@id='newsletter']");

        hightlightElement("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']",getEmailAddress());

        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");

        Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));

        navigateToUrlByJS("https://www.facebook.com");
        Assert.assertEquals(executeForBrowser("return document.domain;"),"facebook.com");

    }
    @Test
    public void TC_02_HTML5(){
        executeForBrowser("window.location='https://sieuthimaymocthietbi.com/account/register'");

        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSecond(3);

        Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"),"Please fill out this field.");

        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Automation");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSecond(3);

        Assert.assertEquals(getElementValidationMessage("//input[@id='firstName']"),"Please fill out this field.");

        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Testing");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Please fill out this field.");

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("aaaa@aa@aa");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Please enter an email address.");
        //var element = $x("//input[@id='lastName']")[0];
        //element.ValidationMessage;
    }
    @AfterClass
    public void cleanBrowser(){

        //driver.quit();
    }
    public String getEmailAddress(){
        Random rand = new Random();
        return "anhvu" + rand.nextInt(99999)+"@gmail.com";
    }
    protected void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Object executeForBrowser(String javaScript){
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }



    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(3);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));

        //var element = $x("//input[@id='lastName']")[0];
        //element.validationMessage;
        //
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }



}
