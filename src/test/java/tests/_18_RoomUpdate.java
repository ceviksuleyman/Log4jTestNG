package tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.Authentication.generateToken;

public class _18_RoomUpdate {

    @Test
    public void roomUpdate(ITestContext context) {

        int id = (int) context.getSuite().getAttribute("room_id");

        Faker faker = new Faker();
        int roomNumber = faker.random().nextInt(99, 9999999);
        boolean status = faker.random().nextBoolean();

        // 1
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("roomNumber", roomNumber);
        jsonObject.put("roomType", "DELUXE");
        jsonObject.put("status", status);
        jsonObject.put("price", 12000);
        jsonObject.put("description", "API Test");

        Response response = given().
                pathParams("1","rooms").
                headers("Authorization", "Bearer " + generateToken()).
                contentType(ContentType.JSON).
                body(jsonObject.toString()).
                when().
                put(ConfigReader.getProperty("medunnaUrl") + "/{1}");

        response.
                then().
                statusCode(200).
                body("roomNumber", equalTo(roomNumber)).
                body("status", equalTo(status)).
                log().body();

    }
}
