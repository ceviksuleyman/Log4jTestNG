package tests;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class _14_UpdateUser {

    Faker faker = new Faker();

    @Test
    public void testUpdateUser(ITestContext context) {

        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "active");

        String bearerToken = "0a82b2b3b6654b6f9e4599f364f1bb115f53d65a7e30899630d99f41764b8008";

        //int id = (int) context.getAttribute("user_id");
        int id = (int) context.getSuite().getAttribute("user_id");

        // put request
        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .pathParams("users", "users", "id", id)
                .body(data.toString())
                .when()
                .put(ConfigReader.getProperty("goRestUrl") + "/{users}/{id}")
                .then()
                .statusCode(200)
                .log().all();

    }
}
