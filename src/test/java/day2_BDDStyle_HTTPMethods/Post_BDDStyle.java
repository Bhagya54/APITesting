package day2_BDDStyle_HTTPMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Post_BDDStyle {
    @Description("Validate the POST request with positive input")
    @Test
    public void positiveTest(){
        String postbody="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}\n";
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .header("Content-Type","application/json")
                .body(postbody)
        .when()
                .log().all().post()
        .then()
                .log().all().statusCode(200);
    }
}
