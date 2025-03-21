package com.thetestingacademy.sampleCheck.RestAssuredBasics.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class APITest008_BDDStyle {

    // Link - https://restful-booker.herokuapp.com/auth
    // Header - Content-Type: application/json
    // Body - {
    //    "username" : "admin",
    //    "password" : "password123"
    //}

    @Description("Verify the POST Request - BDD Style")
    @Test
    public void test_post_BDDStyle(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        RestAssured
                .given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .basePath("/auth")
                    .contentType(ContentType.JSON)
                    .log().all().body(payload)
                .when()
                    .log().all()
                    .post()
                .then()
                    .log().all()
                    .statusCode(200);




    }

}
