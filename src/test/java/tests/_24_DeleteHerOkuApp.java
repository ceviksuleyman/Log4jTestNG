package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static utilities.HerOkuAppAuth.generateTokenHerOkuApp;

public class _24_DeleteHerOkuApp {

    private static Logger logger = LogManager.getLogger(_24_DeleteHerOkuApp.class.getName());

    @Test
    public void testHerOkuDelete(ITestContext context) {

        logger.info("================ Deleting Booking ================");

        int bookingId = (int) context.getSuite().getAttribute("bookingId");

        given()
                .pathParams("1", "booking", "id", bookingId)
                .headers("Cookie", "token=" + generateTokenHerOkuApp(),
                        "Content-Type", "application/json", "Accept", "application/json")
                .when()
                .delete("https://restful-booker.herokuapp.com/{1}/{id}")
                .then()
                .assertThat()
                .statusCode(201)
                .log().all();

        logger.info("================ Booking Deleted =================");
    }
}
