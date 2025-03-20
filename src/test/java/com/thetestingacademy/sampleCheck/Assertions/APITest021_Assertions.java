package com.thetestingacademy.sampleCheck.Assertions;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APITest021_Assertions {

    // TestNG Assertion
    //  Expected Result == Actual Result

//    @Test
//    public void test_hardAssertExample(){
//        System.out.println("Start of the Program");
//        Assert.assertTrue(false);
//        System.out.println("End of the Program");
//    }

    @Test
    public void test_softAssertExample(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false); // This will not stop execution
        System.out.println("This line will be executed");
        softAssert.assertAll(); // This will report all collected errors

    }


}
