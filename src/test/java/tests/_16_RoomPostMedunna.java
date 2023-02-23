package tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static utilities.Authentication.generateToken;

public class _16_RoomPostMedunna {

    @Test
    public void roomPost() throws FileNotFoundException {

        Faker faker = new Faker();

        // 1
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("roomNumber", faker.random().nextInt(999, 999999999));
        jsonObject.put("roomType", "DELUXE");
        jsonObject.put("status", true);
        jsonObject.put("price", 1200);
        jsonObject.put("description", "sample API Test");

        // 2
        File file = new File("src/test/java/testData/roomPost.json");
        FileReader fileReader = new FileReader(file);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject jsonObjectData = new JSONObject(jsonTokener);


        given().pathParams("1", "api", "2", "rooms")
                .headers("Authorization", "Bearer " + generateToken(),
                        "Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .when().post(ConfigReader.getProperty("medunnaUrl") + "/{1}/{2}")
                .then().statusCode(201)
                .log().all();

    }
}
