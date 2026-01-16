package day3_PayloadManagement;

import day3_PayloadManagement.pojos.Booking;
import day3_PayloadManagement.pojos.Bookingdates;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class Payload_POJO {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
    int bookingId;

    @Test(priority = 2)
    public void AssertJAssertionTest() {
        Booking booking = new Booking();
        booking.setFirstname("azai");
        booking.setLastname("venky");
        booking.setTotalprice(222);
        booking.setDepositpaid(true);
        Bookingdates bd = new Bookingdates();
        bd.setCheckin("2018-01-01");
        bd.setCheckout("2019-01-01");
        booking.setBookingdates(bd);
        booking.setAdditionalneeds("Breakfast");

        req = given();
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/booking");
        req.header("Content-Type", "application/json");
        req.body(booking);

        res = req.log().all().post();

        vRes = res.then().log().all();
        bookingId = vRes.extract().path("bookingid");
        String fname = vRes.extract().path("booking.firstname");
        String lname = vRes.extract().path("booking.lastname");
        System.out.println("Booking id: " + bookingId);
        assertThat(bookingId).isNotZero().isNotNull().isPositive();
        assertThat(fname).isEqualTo("azai").isNotNull().isNotBlank().isAlphabetic();
    }
}