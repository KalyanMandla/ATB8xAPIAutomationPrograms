package com.thetestingacademy.sampleCheck.payLoadManagement;

import com.thetestingacademy.sampleCheck.payLoadManagement.gson.Booking;
import com.thetestingacademy.sampleCheck.payLoadManagement.gson.Bookingdates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest023_Payload_Map_POJO {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;


    @Test
    public void testPOSTReq(){
//        {
//            "firstname" : "Jim",
//                "lastname" : "Brown",
//                "totalprice" : 111,
//                "depositpaid" : true,
//                "bookingdates" : {
//            "checkin" : "2018-01-01",
//                    "checkout" : "2019-01-01"
//        },
//            "additionalneeds" : "Breakfast"
//        }



        // POJO -> JSON
        Booking booking = new Booking();
        booking.setFirstname("Pramod");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingDates = new Bookingdates();
        bookingDates.setCheckin("2025-03-20");
        bookingDates.setCheckout("2025-03-20");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);


        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(booking).log().all();

        Response response = requestSpecification.when().post();
        Integer bookingId = response.then().extract().path("bookingid");

        // Get ValidatableResponse to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        System.out.println("Your Booking Id is -> " + bookingId);






    }





}
