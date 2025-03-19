package com.thetestingacademy.sampleCheck.testNGExamples;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITest012_TestNG {

    @BeforeTest
    public void getToken(){
        System.out.println("1");
    }

    @BeforeTest
    public void getBookingID(){
        System.out.println("2");
    }

    @Test
    public void test_Put(){
        // token and BookingID
        System.out.println("3");
    }

    @AfterTest
    public void closeAllTest(){
        System.out.println("close");
    }


}
