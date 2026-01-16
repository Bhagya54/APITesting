package day3_Assertions;


import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class RestAssuredAssertion_Hamcrest {
RequestSpecification req;
Response res;
ValidatableResponse vRes;
int bookingId;
    @Test(priority = 1)
    public void HamcrestAssertionTest(){
        String postPayload="{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
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

        res=req.when().post();

        vRes=res.then();
        vRes.statusCode(200);
        vRes.body("bookingid",notNullValue());
        vRes.body("booking.firstname",equalTo("Jim"));
        vRes.body("booking.lastname",containsString("Brown"));
        vRes.body("booking.depositpaid",equalTo(true));
        vRes.body("booking.totalprice",equalTo(111));
        vRes.body("booking.bookingdates.checkin",equalTo("2018-01-01"));
        vRes.header("Content-Type",equalTo("application/json; charset=utf-8"));
        vRes.time(lessThan(1000L));//Response time greater than 1000 ms
    }


}
