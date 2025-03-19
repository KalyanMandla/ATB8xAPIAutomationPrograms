package com.thetestingacademy.sampleCheck.testNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest020_TestNG_invocationCount {

    @Test(invocationCount = 5)
    public void test01(){
        Assert.assertTrue(true);
    }


}
