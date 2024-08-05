package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static javaSDET.Topic_13_Date.getDateTimeNow;

public class Topic_27_Wait_02 {
    WebDriver driver;
    public void initialBrowser(){

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.facebook.com");
    }
    @Test
    public void TC_01_FindElement(){
        // case 1: Element dc tìm thấy chỉ có 1
        // sẽ không cần chờ hết timeout
        // tìm thấy sẽ trả về 1 web element
        // qua step tiep theo
//        System.out.println(getDateTimeNow());
//        driver.findElement(By.cssSelector("input#email"));
//        System.out.println(getDateTimeNow());

        // case 2: element dc tìm thất nhưng có nhiều hơn 1
        // sẽ không cần chờ hết timeout
        // lấy cái element đầu tiên dù có cả n node
        // qua step tiep theo
//        System.out.println(getDateTimeNow());
//        driver.findElement(By.cssSelector("input[type='email'],[type='password']"));
//        System.out.println(getDateTimeNow());

        //case 3 - Element không dc tìm thấy
        // chờ hết timeout là 10s
        // trong thời gian 10s này cứ mỗi nửa s sẽ tìm lại 1 lần
        // nếu tìm lại mà thấy thì cũng trả về element rồi qua step tiếp theo
        // nếu tìm lại mà ko thấy thì đánh fail testcase và throw exception; NoSuchElementException
//        System.out.println(getDateTimeNow());
//        driver.findElement(By.cssSelector("input[type='email1']"));
//        System.out.println(getDateTimeNow());

    }
    @Test
    public void TC_02_FindElements(){
        List<WebElement> elementList;
        // case 1: Element dc tìm thấy chỉ có 1
        // không cần chờ hết timeout
        // trả về 1 list element
//         System.out.println("Start Step:"+getDateTimeNow());
//         elementList = driver.findElements(By.cssSelector("input#email"));
//         System.out.println("size of list= "+elementList.size());
//         System.out.println("End Step:"+getDateTimeNow());

        // case 2: element dc tìm thất nhưng có nhiều hơn 1
        // ko cần chờ hết timeout 10s
        // trả về list element có nhiều element
        System.out.println(getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input[type='text'],[type='password']"));
        System.out.println("size of list= "+elementList.size());
        System.out.println(getDateTimeNow());

        //case 3 - Element không dc tìm thấy
        // chờ hết timeout là 10s
        // mỗi nửa s cũng tìm lại 1 lần (polling)
        // Nêu trong thời gian tìm lại mà thấy element thì cũng trả về list chứa các element đó
        // nếu hết thời gian tìm lại mà ko thấy thì trả về list rỗng empty và ko đánh fail
        System.out.println(getDateTimeNow());
        elementList= driver.findElements(By.cssSelector("input[type='email1']"));
        System.out.println("size of list= "+elementList.size());
        System.out.println(getDateTimeNow());
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
