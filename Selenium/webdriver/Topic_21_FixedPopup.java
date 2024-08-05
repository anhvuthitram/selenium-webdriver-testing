package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
// https://docs.google.com/document/d/1IZn3_cDV95SRT7RuEl8NWjxw4IPmjwMpd38-HmXfgYM/edit
public class Topic_21_FixedPopup {
    WebDriver driver;
    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    public void beforeClass(){

    }
    @Test
    public void TC_01_fixedPopup_In_DOM() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_")).click();
        Thread.sleep(3000);
        By loginPopup = By.cssSelector("div#modal-login-v1>div.modal-dialog");
        // kiem tra popup hien thi
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("input#password-input")).sendKeys("123456");
        driver.findElement(By.cssSelector("button.btn-login-v1")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(),"Tài khoản không tồn tại!");

        // close the popup
        driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
        Thread.sleep(3000);

        // kiem tra popup ko hien thi
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }
    @Test
    public void TC_02_fixedPopup_In_DOM() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");

        By loginPopup = By.cssSelector("div#k-popup-account-login-mb>div.modal-dialog");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
    }
    @Test
    public void TC_03_fixedPopup_NotIn_DOM() throws InterruptedException {
        driver.get("https://tiki.vn/");
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(3000);
        // verify popup hien thi
        By loginPopup = By.cssSelector("div.ReactModal__Content");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        // Click Dang nhap bang email link
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        Thread.sleep(3000);
        // click Dang nhap button
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(3000);
        //verify message hien thi
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][1]")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][2]")).getText(),"Mật khẩu không được để trống");
        // close popup
        driver.findElement(By.cssSelector("img.close-img")).click();
        Thread.sleep(3000);
        // popup ko con hien thi trong DOM/HTML
        Assert.assertEquals(driver.findElements(loginPopup).size(),0);
    }
    @Test
    public void TC_04_fixedPopup_NotIn_DOM() throws InterruptedException {
        driver.get("https://www.facebook.com/");
        // click button create account
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        Thread.sleep(3000);
        // check popup hien thi
        By signupPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
        Assert.assertTrue(driver.findElement(signupPopup).isDisplayed());
        // click close popup
        driver.findElement(By.cssSelector("img._8idr.img")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElements(signupPopup).size(),0);
    }

    @Test
    public  void TC_06_randomPopup_Not_InDOM() throws InterruptedException {
        // chua chay dc vi cham qua
        driver.get("https://www.javacodegeeks.com/");
        Thread.sleep(3000);
        By newsletterPopup = By.cssSelector("div.lepopup-popup-container>div:not([style ^='display:none'])");
        if (driver.findElements(newsletterPopup).size()>0 && driver.findElements(newsletterPopup).get(0).isDisplayed()) {
            driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style ^='display:none']) div.lepopup-element-html-content>a")).click();
            Thread.sleep(3000);
        } else{
           System.out.println("popup khong hien thi");
        }
        // khong hien thi thi action tiep
        // nhập dữ liệu search
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());

    }
    @Test
    public  void TC_05_randomPopup_InDOM() throws InterruptedException {

        driver.get("https://vnk.edu.vn/");
        Thread.sleep(10000);
        By newsletterPopup = By.cssSelector("div.popmake-content");
        if (driver.findElements(newsletterPopup).size()>0 && driver.findElements(newsletterPopup).get(0).isDisplayed()) {
            driver.findElement(By.cssSelector("div.popmake-content~button")).click();
            Thread.sleep(3000);
        } else{
            System.out.println("Popup không hiển thị");
        }
        driver.findElement(By.xpath("//a[text()='Liên hệ'][@class='mega-menu-link']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content h1")).getText(),"Liên Hệ");
    }
    @Test
    public  void TC_07_randomPopup_Not_In_DOM() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        By marketingPopup = By.cssSelector("div.modal-content.css-modal-bt");
        if (driver.findElement(marketingPopup).isDisplayed()){
            driver.findElement(By.cssSelector("button.close" )).click();
            System.out.println("Popup hiển thị");
        }else
            System.out.println("Popup không hiển thị");
        driver.findElement(By.cssSelector("input.search-form")).sendKeys("Lập dự toán M&E");
        driver.findElement(By.cssSelector("button.header-search")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.course-item-detail a")).getAttribute("title"),"Khóa học Lập dự toán M&E");
    }

        @AfterClass
    public void cleanBrowser(){

       // driver.quit();
    }

}
