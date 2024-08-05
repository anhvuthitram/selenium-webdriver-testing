package javaSDET;

import java.util.ArrayList;
import java.util.List;

public class Topic_15_Generic {
    public static void main(String[] args){
        // List chỉ chứa kiểu dữ liệu String
        List<String> students = new ArrayList<String>();
        students.add("John");
        students.add("Mary");
        students.add("Peter");

        // non Generic
        List addresses = new ArrayList() ;
        addresses.add("123 Main Street");
        addresses.add(14);// Integer
        addresses.add(true);// Boolean
        addresses.add(15.44); // Float





    }
}
