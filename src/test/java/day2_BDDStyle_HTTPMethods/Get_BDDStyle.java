package day2_BDDStyle_HTTPMethods;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class Get_BDDStyle {
    //Base Path is dynamic in nature
    String pincode;

    @Test
    public void PositiveTest(){
        pincode="500032";
        given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pincode)
        .when()
                .log().all().get()
        .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void NegativeTest(){
        pincode="-1";
        given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+pincode)
        .when()
                .get()
        .then()
                .statusCode(404)
                .log().all();
    }
}
