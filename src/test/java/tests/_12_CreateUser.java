package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pojos.GoRestDataPojo;
import utilities.ConfigReader;
import utilities.ObjectMapperUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class _12_CreateUser {

    Faker faker = new Faker();

    @Test
    public void testCreateUser(ITestContext context) throws FileNotFoundException {

        // 1 ----------------
        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "active");

        String bearerToken = "fd745ab45a5fdf46cf4921779944b6a7a940c23821621366dd445a9699f97523";

        // 2 --------------
        String data2 = "{\"name\": \"" + faker.name().fullName() + "\",\n" +
                "   \"email\": \"" + faker.internet().emailAddress() + "\",\n" +
                "   \"gender\": \"Male\",\n" +
                "   \"status\": \"inactive\"\n" +
                "}";
        Map dataMap = ObjectMapperUtils.convertJsonToJava(data2, Map.class);


        // 3 ---------------
        File file = new File("src/test/java/testData/goRestPostBody.json");
        FileReader fileReader = new FileReader(file);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject expectedDataJSONObject = new JSONObject(jsonTokener);

        // 4 ---------------
        GoRestDataPojo dataPojo =
                new GoRestDataPojo(faker.name().fullName(), faker.internet().emailAddress(), "male", "inactive");


        // post request
        Response response = given()
                .headers("Authorization", "Bearer " + bearerToken)
                .pathParam("users", "users")
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post(ConfigReader.getProperty("goRestUrl") + "/{users}");

        response.prettyPrint();

        int id = response.jsonPath().getInt("id");
        System.out.println("Generated id = " + id);

        //context.setAttribute("user_id", id);
        context.getSuite().setAttribute("user_id", id);
    }
}
