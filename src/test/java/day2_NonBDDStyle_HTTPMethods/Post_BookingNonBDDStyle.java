package day2_NonBDDStyle_HTTPMethods;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Post_BookingNonBDDStyle {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;

    @Description("Validate the POST request with positive input")
    @Test
    public void positiveTest(ITestContext context){
        String postbody="{\n" +
                "    \"firstname\" : \"bhagya\",\n" +
                "    \"lastname\" : \"kudupudi\",\n" +
                "    \"totalprice\" : 123,\n" +
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
        req.body(postbody);

        res=req.when().log().all().post();

        vRes=res.then().log().all().statusCode(200);

        context.setAttribute("bookingId",vRes.extract().path("bookingid"));
    }
}
