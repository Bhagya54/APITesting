package day2_NonBDDStyle_HTTPMethods;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Patch_NonBDDStyle {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
    String token = "024c3c468da5c11";
    String bookingId = "3092";
    @Description("Validate the Patch request with positive input")
    @Test
    public void positiveTest(){
        String putBody="{\n" +
                "    \"firstname\" : \"Sarada\",\n" +
                "    \"lastname\" : \"James\"\n" +
                "}";
        req=given();
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/booking/"+bookingId);
        req.contentType(ContentType.JSON);
        req.cookie("token",token);
        req.body(putBody);

        res=req.when().log().all().patch();

        vRes=res.then().log().all().statusCode(200);
    }
    }


