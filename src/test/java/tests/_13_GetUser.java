package tests;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class _13_GetUser {

    @Test
    public void testGetUser(ITestContext context) {

        String bearerToken = "fd745ab45a5fdf46cf4921779944b6a7a940c23821621366dd445a9699f97523";

        //int id = (int) context.getAttribute("user_id"); // this should come from create user request
        int id = (int) context.getSuite().getAttribute("user_id");


        // get request
        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .pathParams("users", "users", "id", id)
                .when()
                .get(ConfigReader.getProperty("goRestUrl") + "/{users}/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
