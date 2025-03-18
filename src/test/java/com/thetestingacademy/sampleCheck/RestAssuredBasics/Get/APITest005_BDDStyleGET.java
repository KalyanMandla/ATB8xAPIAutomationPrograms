package com.thetestingacademy.sampleCheck.RestAssuredBasics.Get;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITest005_BDDStyleGET {

    @Test
    public void test_Get_Req_POSITIVE(){

        String pin_code = "518543";

        RestAssured
                .given()
                    .baseUri("https://api.zippopotam.us")
                    .basePath("/IN/"+pin_code)
                .when()
                    .log()
                    .all()
                    .get()
                .then()
                    .log()
                    .all()
                    .statusCode(200);
    }

    @Test
    public void test_Get_Req_NEGATIVE(){

        String pin_code = "-1";

        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pin_code)
                .when()
                .log()
                .all()
                .get()
                .then()
                .log()
                .all()
                .statusCode(200);
    }
}
