package tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.Authentication.generateToken;

public class _16_RoomPost {

    @Test
    public void roomPost(ITestContext context) throws FileNotFoundException {

        Faker faker = new Faker();
        int roomNumber = faker.random().nextInt(99, 9999999);
        boolean status = faker.random().nextBoolean();

        // 1
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("roomNumber", roomNumber);
        jsonObject.put("roomType", "DELUXE");
        jsonObject.put("status", status);
        jsonObject.put("price", 1200);
        jsonObject.put("description", "API Test");


        // 2
        File file = new File("src/test/java/testData/roomPost.json");
        FileReader fileReader = new FileReader(file);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject jsonObjectData = new JSONObject(jsonTokener);


        Response response = given().
                pathParams("1", "rooms").
                headers("Authorization", "Bearer " + generateToken()).
                contentType(ContentType.JSON).
                body(jsonObject.toString()).
                when().
                post(ConfigReader.getProperty("medunnaUrl") + "/{1}");

        response.
                then().
                statusCode(201).
                body("roomNumber", equalTo(roomNumber)).
                body("status", equalTo(status)).
                log().body();

        int id = response.jsonPath().getInt("id");
        System.out.println("Generated id = " + id);

        context.getSuite().setAttribute("room_id", id);
        context.getSuite().setAttribute("room_number", roomNumber);
    }
}
