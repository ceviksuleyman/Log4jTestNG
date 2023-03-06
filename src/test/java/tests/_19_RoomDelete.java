package tests;

import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static utilities.Authentication.generateToken;

public class _19_RoomDelete {

    @Test
    public void roomDelete(ITestContext context) {

        int id = (int) context.getSuite().getAttribute("room_id");

        Response response = given().
                pathParams("1", "rooms", "2", id).
                headers("Authorization", "Bearer " + generateToken()).
                when().
                delete(ConfigReader.getProperty("medunnaUrl") + "/{1}/{2}");

        response.
                then().
                statusCode(204).
                log().all();
    }
}
