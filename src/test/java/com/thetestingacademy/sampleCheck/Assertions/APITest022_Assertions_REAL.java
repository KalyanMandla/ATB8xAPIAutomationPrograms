package com.thetestingacademy.sampleCheck.Assertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import static org.assertj.core.api.Assertions.*;

public class APITest022_Assertions_REAL {

    // POST - Create -> Verify the Response

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    Integer bookingId;

    public void test_POST() {
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


        // Validatable Response - I - Hamcrest - Rest Assured

        // Rest Assured Default - Hamcrest
        // import org.hamcrest.Matchers;
        validatableResponse.body("booking.firstname", Matchers.equalTo("James"));
        validatableResponse.body("booking.lastname", Matchers.equalTo("Brown"));
        validatableResponse.body("booking.depositpaid", Matchers.equalTo(false));
        validatableResponse.body("bookingid", Matchers.notNullValue());


        // TestNG Assertions

        // SoftAssert vs
        // HardAssert
        // This means that if any assertion fails,
        // the remaining statements in that test method will not be executed

        bookingId = response.then().extract().path("bookingid");
        String firstname = response.then().extract().path("booking.firstname");
        String lastname = response.then().extract().path("booking.lastname");
        Assert.assertNotNull(bookingId);
        Assert.assertEquals(firstname, "James");
        Assert.assertEquals(lastname, "Brown");


        // AssertJ( 3rd - Lib to Assertions)

        assertThat(bookingId).isNotNull().isPositive().isNotZero();
        assertThat(firstname).isEqualTo("James").isNotNull().isNotBlank().isNotEmpty().isAlphanumeric();



    }

}