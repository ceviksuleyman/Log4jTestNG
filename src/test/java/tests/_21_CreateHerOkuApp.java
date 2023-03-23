package tests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pojos.herOkuApp.Booking;
import pojos.herOkuApp.BookingDates;
import pojos.herOkuApp.BookingResponse;
import utilities.HerOkuAppBase;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class _21_CreateHerOkuApp extends HerOkuAppBase {
    private static Logger logger = LogManager.getLogger(_21_CreateHerOkuApp.class.getName());
    @Test
    public void testCreateHerOkuApp(ITestContext context) {

        logger.info("============= Creating Booking =============");

        Faker faker = new Faker();

        spec.pathParams("1", "booking");

        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        int totalprice = faker.random().nextInt(1000, 1200);
        BookingDates bookingDates = new BookingDates("2023-02-01", "2023-03-01");

        Booking postData = new Booking(firstname, lastname, totalprice, false, bookingDates, "Breakfast");


        Response response = given()
                .spec(spec)
                .when()
                .body(postData)
                .post("/{1}");

        response.then()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname", equalTo(firstname))
                .body("booking.lastname", equalTo(lastname))
                .body("booking.totalprice", equalTo(totalprice))
                .log().body();

        BookingResponse actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingResponse.class);

        Assert.assertEquals(actualData.getBooking().getFirstname(), postData.getFirstname());
        Assert.assertEquals(actualData.getBooking().getLastname(), postData.getLastname());

        int bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Generated bookingId = " + bookingId);

        context.getSuite().setAttribute("bookingId", bookingId);

        logger.info("============= Booking is Created =============");
    }
}
