package day2_NonBDDStyle_HTTPMethods;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Get_NonBDDStyle {
    //Base Path is dynamic in nature
    String pincode;
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    @Description("Verify the GET request with positive input")
    @Test
    public void PositiveTest(){
        pincode="500032";
        requestSpecification=given();
        requestSpecification .baseUri("https://api.zippopotam.us");
        requestSpecification .basePath("/IN/"+pincode);

        response=requestSpecification.when().log().all().get();

        validatableResponse=response.then().log().all().statusCode(200);

    }
    @Description("Verify the GET request with negative input: -1")
    @Test
    public void NegativeTest1(){
        pincode="-1";
        requestSpecification=given();
        requestSpecification.baseUri("https://api.zippopotam.us");
        requestSpecification.basePath("/IN/"+pincode);

        response=requestSpecification.when().log().all().get();

        validatableResponse=response.then().log().all().statusCode(404);

    }
    @Description("Verify the GET request with negative input: 12345667788999")
    @Test
    public void NegativeTest2(){
        //long string
        pincode="12345667788999";
        requestSpecification=given();
        requestSpecification.baseUri("https://api.zippopotam.us");
        requestSpecification.basePath("/IN/"+pincode);

        response=requestSpecification.when().log().all().get();

        validatableResponse=response.then().log().all().statusCode(404);

    }

    @Description("Verify the GET request with negative input: $%$^$%")
    @Test
    public void NegativeTest3(){
        //special characters
        pincode="$%$^$%";
        requestSpecification=given();
        requestSpecification.baseUri("https://api.zippopotam.us");
        requestSpecification.basePath("/IN/"+pincode);

        response=requestSpecification.when().log().all().get();

        validatableResponse=response.then().log().all().statusCode(404);
    }

}
