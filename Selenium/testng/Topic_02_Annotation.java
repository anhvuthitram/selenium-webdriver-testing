package testng;

import org.testng.annotations.*;

public class Topic_02_Annotation {
    @BeforeClass
    public void beforeClass(){
        System.out.print("Before Class\n");
    }
    @AfterClass
    public void afterClass(){
        System.out.print("After Class\n");

    }
    @BeforeMethod
    public void BeforeMethod(){
        System.out.print("Before Method\n");
    }
    @AfterMethod
    public void AfterMethod(){
        System.out.print("After Method\n");
    }
    @BeforeSuite
    public void BeforeSuite(){
        System.out.print("Before Suite\n");
    }
    @AfterSuite
    public void AfterSuite(){
        System.out.print("After Suite\n");
    }
    @BeforeTest
    public void BeforeTest(){
        System.out.print("Before Test\n");
    }
    @AfterTest
    public void AfterTest(){
        System.out.print("After Test\n");
    }
    @Test
    public void Test01(){
        System.out.print("Test01\n");
    }
    @Test
    public void Test02(){
        System.out.print("Test02\n");
    }
    @Test
    public void Test03(){
        System.out.print("Test03\n");
    }
}
