package day3_PayloadManagement;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import java.util.LinkedHashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class Payload_HashMap {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
    int bookingId;

    @Test(priority = 2)
    public void AssertJAssertionTest() {
        Map<String, Object> postPayload = new LinkedHashMap<>();
        postPayload.put("firstname", "azai");
        postPayload.put("lastname", "venky");
        postPayload.put("totalprice", 123);
        postPayload.put("depositpaid", false);

        Map<String,Object> bookingDate = new LinkedHashMap<>();
        bookingDate.put("checkin","2018-01-01");
        bookingDate.put("checkout","2019-01-01");
        postPayload.put("bookingdates", bookingDate);

        postPayload.put("additionalneeds", "Breakfast");
        req = given();
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/booking");
        req.header("Content-Type", "application/json");
        req.body(postPayload);

        res = req.post();

        vRes = res.then();
        bookingId = vRes.extract().path("bookingid");
        String fname = vRes.extract().path("booking.firstname");
        String lname = vRes.extract().path("booking.lastname");
        System.out.println("Booking id: " + bookingId);
        assertThat(bookingId).isNotZero().isNotNull().isPositive();
        assertThat(fname).isEqualTo("azai").isNotNull().isNotBlank().isAlphabetic();
    }
}