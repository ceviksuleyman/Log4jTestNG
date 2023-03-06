package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.Authentication.generateToken;

public class _17_RoomGet {

    @Test
    public void roomGetTest(ITestContext context) {

        int id = (int) context.getSuite().getAttribute("room_id");
        int room_number = (int) context.getSuite().getAttribute("room_number");

        given().
                pathParams("1", "rooms", "2", id).
                headers("Authorization", "Bearer " + generateToken()).
                when().
                get(ConfigReader.getProperty("medunnaUrl") + "/{1}/{2}").
                then().
                statusCode(200).
                body("roomNumber", equalTo(room_number)).
                log().all();
    }
}
