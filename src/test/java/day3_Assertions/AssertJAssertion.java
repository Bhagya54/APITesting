package day3_Assertions;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
import static io.restassured.RestAssured.given;

public class AssertJAssertion {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
    int bookingId;
    @Test(priority = 2)
    public void AssertJAssertionTest(){
        String postPayload="{\n" +
                "    \"firstname\" : \"Kaira\",\n" +
                "    \"lastname\" : \"Agarwal\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        req=given();
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/booking");
        req.header("Content-Type","application/json");
        req.body(postPayload);

        res=req.post();

        vRes=res.then();
        bookingId=vRes.extract().path("bookingid");
        String fname=vRes.extract().path("booking.firstname");
        String lname=vRes.extract().path("booking.lastname");
        System.out.println("Booking id: " + bookingId);
        assertThat(bookingId).isNotZero().isNotNull().isPositive();
        assertThat(fname).isEqualTo("Kaira").isNotNull().isNotBlank().isAlphabetic();
        // isEmpty() - ""
        // isBlank - " "

    }
}
