package tests;

import com.github.javafaker.Faker;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pojos.herOkuApp.Booking;
import pojos.herOkuApp.BookingDates;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static utilities.HerOkuAppAuth.generateTokenHerOkuApp;


public class _23_UpdateHerOkuApp {

    @Test
    public void testHerOkuUpdate(ITestContext context) {

        Faker faker = new Faker();
        int bookingId = (int) context.getSuite().getAttribute("bookingId");


        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();

        BookingDates bookingDates = new BookingDates("2023-02-01", "2023-03-01");

        Booking putData = new Booking(firstname, lastname, 1200, true, bookingDates,
                "Dinner");
        //System.out.println("putData = " + putData);

        given()
                .pathParams("1", "booking", "id", bookingId)
                .headers("Cookie", "token=" + generateTokenHerOkuApp(),
                        "Content-Type", "application/json", "Accept", "application/json")
                .when()
                .body(putData)
                .put("https://restful-booker.herokuapp.com/{1}/{id}")

                .then()
                .assertThat()
                .statusCode(200)
                .body("firstname", equalTo(firstname))
                .body("lastname", equalTo(lastname))
                .log().body();
    }
}
