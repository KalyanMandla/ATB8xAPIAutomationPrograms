package com.thetestingacademy.sampleCheck.RestAssuredBasics.GET;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITest007_GET_TESTNG {

    @Test
    public void test_GET_Request(){

        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/1")
                .when()
                .get()
                .then().log().all()
                .statusCode(200);

    }
}
