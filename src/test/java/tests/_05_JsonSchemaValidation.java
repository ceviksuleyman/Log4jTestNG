package tests;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static utilities.Authentication.generateToken;

public class _05_JsonSchemaValidation {

    /*
    Json --> json schema converter

    https://jsonformatter.org/json-to-jsonschema
     */
    @Test
    public void jsonSchemaValidation() {

        String url = "https://www.medunna.com/api/physicians/309705";
        //String url = "https://medunna.com/api/rooms/394029";

        Response res = given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + generateToken())
                .when()
                .get(url);

        res.prettyPrint();


        // JsonSchema
        String roomJsonSchemaFilePath = "src/test/java/testData/RoomJsonSchema.json";
        String physicianJsonSchemaFilePath = "src/test/java/testData/PhysicianJsonSchema.json";

        res.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(new File(physicianJsonSchemaFilePath)));
    }
}
