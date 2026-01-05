package day2_NonBDDStyle_HTTPMethods;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Put_NonBDDStyle {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
    String token = "13a19c297e44304";
    String bookingId = "3649";
    @Description("Validate the PUT request with positive input")
    @Test
    public void positiveTest(){
        String putBody="{\n" +
                "    \"firstname\" : \"Jaasritha\",\n" +
                "    \"lastname\" : \"Guthula\",\n" +
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
        req.basePath("/booking/"+bookingId);
        req.contentType(ContentType.JSON);
        req.cookie("token",token);
        req.body(putBody);

        res=req.when().log().all().put();

        vRes=res.then().log().all().statusCode(200);
    }
    }


