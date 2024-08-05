package webdriver;


import org.openqa.selenium.*;
import org.testng.Assert;

public class Topic_07_WebElement_Commands {

    WebDriver driver;
    WebElement element;
    public void TC_01_WebElement(){
        driver.findElement(By.xpath("")).click();

        element=driver.findElement(By.xpath(""));

        // click vào các element dạng button/ checkbox/radio/link/image
        element.click();

        // Nhập dữ liệu dang textbox/text area/ drop-down
        element.clear(); // xóa dữ liễu truóc khi send key
        element.sendKeys("anh@yahoo.com");
        element.sendKeys(Keys.ENTER);

        driver.findElement(By.id("div.login-page"))
                .findElement(By.cssSelector("div.customer-blocks"))
                        .findElement(By.id("Email"));
        // submit chỉ có tác dụng với form(Signup/login/add/update)
        // thẻ form
        driver.findElement(By.id("Email")).sendKeys("");
        driver.findElement(By.id("Password")).sendKeys("");
        driver.findElement(By.id("Password")).submit();


        //Áp dụng cho tất cả các element
        // kiễm tra element có hiển thị hay không
        // Size > : width / height >0
        // nhìn thấy/ thao tác được
        element.isDisplayed();
        element.isSelected();
        Assert.assertTrue(element.isDisplayed());

        // ap dung cho checkbox, radio, dropdown(default)
        // kiem tra element da dc chon hay chua
        element.isSelected();

        // ap dung cho tat ca cac loai
        // kiem tra 1 element co bi disable hay ko
        element.isEnabled();
        // GUI : font size color position Location
        element.getCssValue("background-color"); //#f82573
        // ap dung cho element có text  (link.button,header, label)
        element.getText();

        element.getAttribute("placeholder");

        element.getSize();
        // vị trí của element so với view port
        Point pointElement = element.getLocation();

        Rectangle rectangle= element.getRect();

        // size
        rectangle.getWidth();
        rectangle.getHeight();
        rectangle.getDimension();

        // location
        rectangle.getX();
        rectangle.getY();
        rectangle.getPoint();

        //lay the html cua element đó
        // element A
       String tagName= driver.findElement(By.cssSelector("#FirstName")).getTagName();

        // element B
        driver.findElement(By.xpath(""+tagName+"[@id='LastName']"));


        // dung cho Chrome
        element.getAccessibleName();












    }



}
