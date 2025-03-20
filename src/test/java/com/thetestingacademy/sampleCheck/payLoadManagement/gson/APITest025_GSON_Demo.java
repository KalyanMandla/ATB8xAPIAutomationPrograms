package com.thetestingacademy.sampleCheck.payLoadManagement.gson;

import com.google.gson.Gson;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import static org.assertj.core.api.Assertions.*;

public class APITest025_GSON_Demo {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Test
    public void testPositive(){

        // Step 1 - POST
        // URL -> BASE URI + Base PATH
        // HEADER
        // BODY
        // AUTH -NO

        // Step 2
        // Prepare the Payload( Object -> JSON String)
        // send the request

        // Step 3
        // Validate Response ( JSON String -> Object)
        // FirstName
        // Status Code
        // Time Response

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

        Gson gson = new Gson();
        // Object -> JSON String (GSON) -> Ser
        String jsonStringBooking = gson.toJson(booking);
        System.out.println(jsonStringBooking);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonStringBooking).log().all();

        Response response = requestSpecification.when().post();
        String  jsonResonseString = response.asString();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        // Case 1 extract, jsonPath().getString() -  Response is small
        // Case 2 Response -> complex JSON - HUGE JSON

        // String - Object - De Ser

        BookingResponse bookingResponse = gson.fromJson(jsonResonseString, BookingResponse.class);

        assertThat(bookingResponse.getBookingid()).isNotZero().isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Pramod").isNotNull().isNotEmpty();




    }
















}
