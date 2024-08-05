package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_XPath_Css {
    WebDriver driver;
    public void initialBrowser(){

    }
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void TC_01_Register_EmptyData(){
        // Arrange
        driver = new FirefoxDriver();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Action
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // Verify
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
    }
    @Test
    public void TC_02_Register_InvalidEmail(){
        driver = new FirefoxDriver();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Anh");
        driver.findElement(By.id("txtEmail")).sendKeys("Anh@123@123");
        driver.findElement(By.id("txtCEmail")).sendKeys("Anh@123@123");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0912345678");
        //..
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // Assert
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }
    @Test
    public void TC_03_Register_InvalidConfirmEmail() {
        driver = new FirefoxDriver();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Anh");
        driver.findElement(By.id("txtEmail")).sendKeys("AnhVu@yahoo.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("AnhVu@yahoo.net");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0912345678");
        //..
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // Assert
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");

    }
    @Test
    public void TC_04_Register_PasswordLessThan6Chars() {
        driver = new FirefoxDriver();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Anh");
        driver.findElement(By.id("txtEmail")).sendKeys("AnhVu@yahoo.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("AnhVu@yahoo.com");
        driver.findElement(By.id("txtPassword")).sendKeys("12345");
        driver.findElement(By.id("txtCPassword")).sendKeys("12345");
        driver.findElement(By.id("txtPhone")).sendKeys("0912345678");
        //..
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // Assert
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }
    @Test
    public void TC_05_Register_IncorrectConfirmPassword() {
        driver = new FirefoxDriver();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Action
        driver.findElement(By.id("txtFirstname")).sendKeys("Anh");
        driver.findElement(By.id("txtEmail")).sendKeys("AnhVu@yahoo.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("AnhVu@yahoo.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("0912345678");
        //..
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // Assert
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
    }
    @Test
    public void TC_06_Register_InvalidPhoneNumber() {
        driver = new FirefoxDriver();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //Action
        driver.findElement(By.id("txtFirstname")).sendKeys("anh");
        driver.findElement(By.id("txtEmail")).sendKeys("anhvu@yahoo.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("anhvu@yahoo.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
        driver.findElement(By.id("txtPhone")).sendKeys("0912345678901");
        //..
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
        //Action
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("123456");
        //..
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }
        @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}
