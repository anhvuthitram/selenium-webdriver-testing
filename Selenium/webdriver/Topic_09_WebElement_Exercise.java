package webdriver;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
//https://docs.google.com/document/d/1G0QsiVDI5KfhNGKxE6a03bhn6KpvgDh69BTx2ttCO94/edit
public class Topic_09_WebElement_Exercise {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void beforeClass(){

    }
    @Test
    public void TC_01_isDisplayed(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
        if (emailTextbox.isDisplayed()) {
            System.out.println("Element is displayed");
            emailTextbox.sendKeys("Automation Testing");
        }
        else System.out.println("Element is not displayed");

        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
        if (ageUnder18Radio.isDisplayed()) {
            System.out.println("Element is displayed");
            ageUnder18Radio.click();
        }
        else System.out.println("Element is not displayed");

        WebElement educationTextbox = driver.findElement(By.cssSelector("textarea#edu"));
        if (educationTextbox.isDisplayed()) {
            System.out.println("Element is displayed");
            educationTextbox.sendKeys("Automation Testing");
        }
        else System.out.println("Element is not displayed");

        WebElement user5TextField = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
    }
    @Test
    public void TC_02_Enable(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
        if(emailTextbox.isEnabled()){
            System.out.println("emailTextbox is enabled");
        }
        else
            System.out.println("ageUnder18Radio is disable");

        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
        if (ageUnder18Radio.isEnabled()) {
            System.out.println("ageUnder18Radio is enabled");
        }
        else System.out.println("ageUnder18Radio is disables");

        WebElement educationTextbox = driver.findElement(By.cssSelector("textarea#edu"));
        if (educationTextbox.isEnabled()) {
            System.out.println("educationTextbox is enabled");
        }
        else System.out.println("educationTextbox is disabled");

        WebElement jobRole1Textbox = driver.findElement(By.cssSelector("select#job1"));
        if(jobRole1Textbox.isEnabled()){
            System.out.println("jobRole1Textbox is enabled");
        }
        else System.out.println("jobRole1Textbox is disabled");

        WebElement interestDevelopmentCheckbox = driver.findElement(By.cssSelector("input#development"));
        if(interestDevelopmentCheckbox.isEnabled()){
            System.out.println("interestDevelopmentCheckbox is enabled");
        }
        else System.out.println("interestDevelopmentCheckbox is disabled");

        WebElement slider01 = driver.findElement(By.cssSelector("input#slider-1"));
        if(slider01.isEnabled()){
            System.out.println("slider01 is enabled");
        }
        else System.out.println("slider01 is disabled");

        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#disable_password"));
        if(passwordTextbox.isEnabled()==false){
            System.out.println("passwordTextbox is disabled");
        }
        else System.out.println("passwordTextbox is enabled");

        WebElement interestCheckbox = driver.findElement(By.cssSelector("input#radio-disabled"));
        if(interestCheckbox.isEnabled()==false){
            System.out.println("ageRadio is disabled");
        }
        else System.out.println("ageRadio is enabled");
    }
    @Test
    public void TC_03_Selected(){
        //isSelected: Kiểm tra 1 element dc chọn thành công (Radio/Checkbox/Dropdown)
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement ageCheckbox = driver.findElement(By.cssSelector("input#under_18"));
        if(ageCheckbox.isSelected()){
            System.out.println("ageCheckbox is selected");
        }
        else
            System.out.println("ageCheckbox is unselected");

        WebElement interestCheckbox = driver.findElement(By.cssSelector("input#development"));
        if(interestCheckbox.isSelected()){
            System.out.println("interestCheckbox is selected");
        }
        else
            System.out.println("interestCheckbox is unselected");
        ageCheckbox.click();
        interestCheckbox.click();
        if(ageCheckbox.isSelected()){
            System.out.println("ageCheckbox is selected");
        }
        else
            System.out.println("ageCheckbox is unselected");
        if(interestCheckbox.isSelected()){
            System.out.println("interestCheckbox is selected");
        }
        else
            System.out.println("interestCheckbox is unselected");
    }
    @Test
    public void TC_04_MailChimp_Register_Validation() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("hanala2002@yahoo.com");
        driver.findElement(By.cssSelector("input#email")).sendKeys(Keys.TAB);

        //password only number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //password only character
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("auto");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //password only upper cases
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("AUTO");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //password only special characters
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("!@#$%&");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());


        //password 8 char
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("Auto123!@#");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);
        Thread.sleep(3000);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

    }
    @Test
    public void TC_01_Login_Empty_Email_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.block-title~ul>li>a[title='My Account']")).click();
        driver.findElement(By.id("send2")).click();
       // Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");

    }
    @Test
    public void TC_02_Login_Invalid_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.block-title~ul>li>a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("aaa@aa.111");
        driver.findElement(By.id("send2")).click();
        Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }
    @Test
    public void TC_03_Login_Invalid_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.block-title~ul>li>a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input#pass")).sendKeys("1234");
        driver.findElement(By.id("send2")).click();
        Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

    }
    @Test
    public void TC_04_Incorrect_Email_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        driver.findElement(By.id("send2")).click();
        //Assert.assertEquals(driver.findElement(By.xpath("//li//span")).getText(),"Invalid login or password.");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");

    }

    @AfterClass
    public void cleanBrowser(){
     //   driver.quit();
    }



}
