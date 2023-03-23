package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static utilities.HerOkuAppAuth.generateTokenHerOkuApp;

public class _22_ReadHerOkuApp {
    private static Logger logger = LogManager.getLogger(_22_ReadHerOkuApp.class.getName());

    @Test
    public void testHerOkuRead(ITestContext context) {

        logger.info("============== Reading Booking Info ==============");

        int bookingId = (int) context.getSuite().getAttribute("bookingId");

        given()
                .pathParams("1", "booking", "id", bookingId)
                .headers("Cookie", "token=" + generateTokenHerOkuApp(),
                        "Content-Type", "application/json", "Accept", "application/json")
                .when()
                .get("https://restful-booker.herokuapp.com/{1}/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .log().body();

        logger.info("============== Booking info is Displayed ==============");
    }
}
