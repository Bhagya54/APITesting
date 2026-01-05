package day2_NonBDDStyle_HTTPMethods;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Post_NonBDDStyle {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
    @Description("Validate the POST request with positive input")
    @Test
    public void positiveTest(){
        String postbody="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}\n";
        req=given();
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/auth");
        req.header("Content-Type","application/json");
        req.body(postbody);

        res=req.when().log().all().post();

        vRes=res.then().log().all().statusCode(200);
    }
}
