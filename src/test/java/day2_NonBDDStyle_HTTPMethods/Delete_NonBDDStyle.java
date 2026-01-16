package day2_NonBDDStyle_HTTPMethods;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Delete_NonBDDStyle {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
    String token = "45057502d983826";
    String bookingId = "3092";
    @Description("Validate the Delete request with positive input")
    @Test
    public void positiveTest(){

        req=given();
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/booking/"+bookingId);
        req.cookie("token",token);


        res=req.when().log().all().delete();

        vRes=res.then().log().all().statusCode(201);
    }
    }


