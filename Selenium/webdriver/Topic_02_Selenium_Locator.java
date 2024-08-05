package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String fullName="Selenium Locator";
    @BeforeClass
    public void TC_00_Run_On_Firefox() {
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/register");

    }
    public void TC01_ID()   {
        driver.findElement(By.id("small-searchterms")).sendKeys("Macbook");
        driver.findElement(By.id("FirstName")).sendKeys("Anh");
    }
    public void TC02_Class(){
        // giá tri trong class mà không có khoảng trắng lấy toàn bộ
        // giá trị có khoảng trắng lấy phần duy nhất
        driver.findElement(By.className("register-next-step-button")).click();
    }
    public void TC03_Name(){
        driver.findElement(By.name("DateOfBirthDay")).isDisplayed();
        driver.findElement(By.name("DateOfBirthMonth")).isDisplayed();
        driver.findElement(By.name("DateOfBirthYear")).isDisplayed();
    }
    public void TC04_LinkText(){
        // chi lam viec voi link
        // the a
        driver.findElement(By.linkText("Register"));
        driver.findElement(By.linkText("Log in"));

    }

    public void TC05_Partial_Link_Text(){
        // chi lam viec voi element va link
        // có thể lấy hết toàn bộ text hoặc 1 phần
        driver.findElement(By.partialLinkText("download"));
        driver.findElement(By.partialLinkText("Digital"));
        driver.findElement(By.partialLinkText("Register"));
    }

    public void TC06_Tagname() {
    // khi cần tìm element giống nhau textbox/radio/link
        driver.findElements(By.tagName("button"));
        driver.findElements(By.tagName("input"));
        driver.findElements(By.tagName("label"));
    }

    public void TC07_CSS() {
        driver.findElement(By.cssSelector("input#Company"));
        driver.findElement(By.cssSelector("#Company"));
        driver.findElement(By.cssSelector("input[id='Company']"));

        driver.findElement(By.cssSelector("button.register-next-step-button"));
        driver.findElement(By.cssSelector("button[class='button-1 register-next-step-button']"));

        driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"));
        driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"));
        driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"));

        driver.findElement(By.cssSelector("a[href*='register?']"));
        driver.findElement(By.cssSelector("a[href*='login?']"));


        driver.findElement(By.cssSelector("a[href='/register?returnUrl=%2Fregister']"));
        driver.findElement(By.cssSelector("a[href='/login?returnUrl=%2Fregister']"));

        driver.findElement(By.cssSelector("a"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("input"));

    }

    public void TC08_XPath() {
        driver.findElement(By.xpath("//input[@id='small-searchterms']"));
        driver.findElement(By.xpath("//input[@id='Password']"));
        driver.findElement(By.xpath("//input[@id='Company']"));
        driver.findElement(By.xpath("//button[@class='button-1 register-next-step-button']"));
        driver.findElement(By.xpath("//button[contains(@class,'register-next-step-button')]"));
        driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));
        driver.findElement(By.xpath("//a[text()='Register']"));
        driver.findElement(By.xpath("//a[text()='Log in']"));
        driver.findElement(By.xpath("//a[contains(text(),'Shipping')]"));
        driver.findElement(By.xpath("//a"));


    }
    @Test
    public void TC09_Relative_Locator(){
        // khi không thể định danh được element của chính nó , nên phải dựa vào vị trí bên cạnh
        // su dung de test giao dien position khớp design không
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/login");
        By passwordTextboxBy = By.cssSelector("input#Password");
        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#Password"));
        By rememberCheckboxBy = By.id("RememberMe");
        By forgotPasswordLinkBy = By.cssSelector("span.forgot-password");
        By loginButtonBy = By.cssSelector("button.login-button");
        WebElement rememberMeLabelText = driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButtonBy)
                .below(passwordTextboxBy)
                .toRightOf(rememberCheckboxBy)
                .toLeftOf(forgotPasswordLinkBy));
    }

    @AfterClass
    public void cleanBrowser(){
         driver.quit();
    }

}
