package day4_GSON;

import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class POST_Booking_Test_GSON {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
    @Test
    public void createBookingTest(){
        Gson gson = new Gson();
        //gson.toJson(javaObjectRef)
        //gson.fromJson(JsonString,className.class)
        Booking booking = new Booking();
        booking.setFirstname("Bhagya");
        booking.setLastname("Sahitya");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);
        Bookingdates bd = new Bookingdates();
        bd.setCheckin("12/12/2018");
        bd.setCheckout("12/12/2019");
        booking.setBookingdates(bd);

        //Java Object to Json String
        String payload=gson.toJson(booking);
        System.out.println(payload);


        req = given();
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/booking");
        req.header("Content-Type", "application/json");
        req.body(payload);

        res = req.log().all().post();
        String jsonResponseString=res.asString();

        vRes=res.then().log().all();
        vRes.statusCode(200);
        vRes.body("booking.firstname",equalTo("Bhagya"));

        //case 1: Using extract() method
        String firstName=vRes.extract().path("booking.firstname");
        System.out.println("First Name using extract method: " + firstName);

        //case 2: Using jsonPath() method
        JsonPath jsonPath = new JsonPath(jsonResponseString);
        String lastname= jsonPath.getString("booking.lastname");
        int bookingId = jsonPath.getInt("bookingid");
        System.out.println("Last Name using jsonPath: " + lastname);
        System.out.println("Booking id using jsonPath: " + bookingId);

        //Case 3: Using POJO - Deserialization - Convert json String response to Java Object
        BookingResponse bookingRes = gson.fromJson(jsonResponseString, BookingResponse.class);


        //TestNG Assertion
        Assert.assertEquals(bookingRes.getBooking().getFirstname(),"Bhagya");

        //assertJ
        assertThat(bookingRes.getBookingid()).isPositive().isNotNull().isNotZero();
        assertThat((bookingRes.getBooking().getLastname())).isNotBlank().isNotEmpty().isEqualTo("Sahitya");

    }
}
