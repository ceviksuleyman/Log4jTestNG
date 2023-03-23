package tests;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static utilities.HerOkuAppAuth.generateTokenHerOkuApp;

public class _22_ReadHerOkuApp  {

    @Test
    public void testHerOkuRead(ITestContext context) {

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
    }
}
