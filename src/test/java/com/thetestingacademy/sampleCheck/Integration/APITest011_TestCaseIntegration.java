package com.thetestingacademy.sampleCheck.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest011_TestCaseIntegration {
    // Create a token
    // Create a booking
    // Perform a PUT Request
    // Verify that PUT is success by GET Request
    // Delete the ID
    // Verify it doesn't exist GET Request

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    String token;
    String bookingId;


    public String getToken(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload);

        // When() - Response
        response = requestSpecification.when().post();

        // Then - ValidatableResponse
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        // Extract the token
        token = response.jsonPath().getString("token");
        System.out.println(token);
        return token;
    }

    public String getBookingId(){
        String payload_POST = "{\n" +
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

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST).log().all();

        response = requestSpecification.when().post();

        // Get ValidatableResponse to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        bookingId = response.jsonPath().getString("bookingid");
        System.out.println(bookingId);
        return bookingId;
    }

    @Test(priority = 1)
    public void test_update_request_put(){
        token = getToken();
        bookingId = getBookingId();
        System.out.println(token);
        System.out.println(bookingId);


        String payload_PUT = "{\n" +
                "            \"firstname\" : \"Kalyan\",\n" +
                "            \"lastname\" : \"Brown\",\n" +
                "            \"totalprice\" : 111,\n" +
                "            \"depositpaid\" : true,\n" +
                "            \"bookingdates\" : {\n" +
                "                \"checkin\" : \"2018-01-01\",\n" +
                "                \"checkout\" : \"2019-01-01\"\n" +
                "            },\n" +
                "            \"additionalneeds\" : \"Breakfast\"\n" +
                "        }";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payload_PUT).log().all();

        response = requestSpecification.when().put();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }

    @Test(priority = 2)
    public void test_update_request_get(){
        System.out.println(bookingId);
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingId);
        response = requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(200);

        String firstname = response.jsonPath().getString("firstname");
        Assert.assertEquals(firstname,"Kalyan");

    }

    @Test(priority = 3)
    public void test_delete_booking(){
        System.out.println(bookingId);
        System.out.println(token);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        response = requestSpecification.when().delete();

        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201); // #TODO #1 - Delete Bug
    }

    @Test(priority = 4)
    public void test_after_delete_request_get(){
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingId);
        response = requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(404);
    }

    // Todo #2 - I will add more assertions here

}
