package tests;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class _15_DeleteUser {

    @Test
    public void testDeleteUser(ITestContext context) {

        String bearerToken = "0a82b2b3b6654b6f9e4599f364f1bb115f53d65a7e30899630d99f41764b8008";

        //int id = (int) context.getAttribute("user_id");
        int id = (int) context.getSuite().getAttribute("user_id");

        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .pathParams("users", "users", "id", id)
                .when()
                .delete(ConfigReader.getProperty("goRestUrl") + "/{users}/{id}")
                .then()
                .statusCode(204)
                .log().all();
    }
}
