package testng;

import org.testng.Assert;

public class Topic_01_Assertion {
    public static void main(String[] args) {
        // 3 hàm chính để kiểm tra tính đúng đắn dữ liệu
        boolean gender = 3<5;
        // kiểm tra dữ liệu phải ĐÚNG
        Assert.assertTrue(gender);

        // kiểm tra dữ liệu phải SAI
        Assert.assertFalse(3>5);

        // kiểm tra dữ liệu phải bằng mong đợi (ACTUAL EXPECTED)
        // kieu du lieu giong nhau
        // gia tri dua du lieu bang nhau
        Assert.assertEquals(5,6);
        Assert.assertEquals("name","NAME");
    }
}
