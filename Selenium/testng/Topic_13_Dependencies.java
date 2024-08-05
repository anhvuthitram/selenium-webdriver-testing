package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
@Listeners(listeners.ExtendReport.class)
public class Topic_13_Dependencies {

    WebDriver driver;


    @BeforeClass
    public void BeforeClass()   {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30))   ;
    }
    @Test
    public void TC_01_CreateNewUser() {
        System.out.println("Run TC01");
    }
    @Test(dependsOnMethods ="TC_01_CreateNewUser" )
    public void TC_02_ViewAndSearchUser() {
        System.out.println("Run TC02");
    }
    @Test(dependsOnMethods ="TC_02_ViewAndSearchUser" )
    public void TC_03_UpdateExistingUser() {
        Assert.assertTrue(false);
    }
    @Test(dependsOnMethods ="TC_03_UpdateExistingUser" )
    public void TC_04_MoveExistingUserToOtherRole() {
        System.out.println("Run TC04");
    }
    @Test(dependsOnMethods ="TC_04_MoveExistingUserToOtherRole" )
    public void TC_05_DeleteUser() {
        System.out.println("Run TC05");
    }
    @AfterClass
    public void AfterClass() {
         driver.close();
    }

}
