package javaSDET;

import org.bouncycastle.crypto.digests.TupleHash;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.*;


public class Topic_01_DataType {

    // cach khai bao
    // Access Modifier: pham vi truy cap private/public/void/default
    //1 Access Modifier - kieu du lieu - ten bien- gia tri bien (ngoai ham/trong ham deu duoc)
    public char cName = 'b';

    //2.1 Access Modifier - Ten bien
    private char cAddress;
    //2.2 Ten bien - Gia tri ga'n sau (Ham)
    public void clickToElement(){
        cAddress = 'c';
        char cCity ='d';
    }
    // Nhom 1-: kieu du lieu nguyen thuy
    //char: ky tu
    //khi gan gia tri khoi tao thi nam trong dau nhay don (')
    // byte /short /int /long: so nguyen
    byte bNumber = 127;
    short sNumber = 1200;
    int iNumber=350000;
    long lNumber = 234240235;
    float fNumber = 15.7f;
    double dNumber = 15.21234d;
    boolean gender = false;

    //Khi gan gia tri khoi tao thi khong nam trong dau gi ca
    // float/double: so thuc
    //Khi gan gia tri khoi tao thi khong nam trong dau gi ca
    // boolean: logic
    //Khi gan gia tri khoi tao thi khong nam trong dau gi ca

    // Nhom 2- kieu du lieu tham chieu (refernce type/non-primitive type)
    //string: chuoi
    //Khi gan gia tri khoi tao thi  nam trong dau nhay doi (")

    // class Interface Array List/Set Map Object
    String fullName = "Anh Vu";
    // Class
    FirefoxDriver fDriver= new FirefoxDriver();
    Actions actions = new Actions(fDriver);
    Topic_01_DataType topic01 = new Topic_01_DataType();

    // Interface
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    // Array
    String[] studentName = {"Anh", "A","Test"};
    Integer[] studentPhone ={98279837,234538, 4535};

    //List / Set/Queue
    List<String> studentAddress= new ArrayList<String>();
    List<String> studentCity =new LinkedList<String>();

    // Map
    Map<String, Integer> zip = new HashMap<String, Integer>();

    //Object
    Object name ="Nam";
    Object Phone = 2142;
}
