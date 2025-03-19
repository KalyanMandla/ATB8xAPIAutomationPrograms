package com.thetestingacademy.sampleCheck.RestAssuredBasics.PUT;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest010_PUT_NonBDDStyle {

    RequestSpecification requestSpecification = RestAssured.given();

//    public void get_token(){
//
//    }
//
//    public void get_booking_id(){
//
//    }

    //Token - {"token":"45d5999a320bd01"}

// {"bookingid":4063,"booking":{"firstname":"Jim","lastname":"Brown","totalprice":111,"depositpaid":true,"bookingdates":{"checkin":"2018-01-01","checkout":"2019-01-01"},"additionalneeds":"Breakfast"}}


    @Description("Verify the PUT Request for the Restful Booker APIs")
    @Test
    public void test_put_NonBDD(){
        // Booking id
        // token - 111
//         payload - {
//            "firstname" : "James",
//            "lastname" : "Brown",
//            "totalprice" : 111,
//            "depositpaid" : true,
//            "bookingdates" : {
//                "checkin" : "2018-01-01",
//                "checkout" : "2019-01-01"
//            },
//            "additionalneeds" : "Breakfast"
//        }

        String token = "ab24be3a7eb4f65";
        String bookingid = "836";
        String payload_PUT = "{\n" +
                "            \"firstname\" : \"James\",\n" +
                "            \"lastname\" : \"Brown\",\n" +
                "            \"totalprice\" : 111,\n" +
                "            \"depositpaid\" : true,\n" +
                "            \"bookingdates\" : {\n" +
                "                \"checkin\" : \"2018-01-01\",\n" +
                "                \"checkout\" : \"2019-01-01\"\n" +
                "            },\n" +
                "            \"additionalneeds\" : \"Breakfast\"\n" +
                "        }";

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingid);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payload_PUT).log().all();

        Response response = requestSpecification.when().put();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);





    }

}
