package day1;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class Testcase_01 {
    @Test
    public void BasicTest(){
        given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/BE/1000")
        .when()
                .get()
        .then()
                .statusCode(200)
                .log().all();
    }
}
