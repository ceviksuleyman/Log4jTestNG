package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HerOkuAppAuth {


    public static String generateTokenHerOkuApp() {

        String url = "https://restful-booker.herokuapp.com/auth";
        JSONObject tokenBody = new JSONObject();
        tokenBody.put("username", "admin");
        tokenBody.put("password", "password123");

        Response response = given().
                contentType(ContentType.JSON).
                body(tokenBody.toString()).
                when().post(url);

        return response.jsonPath().getString("token");
    }
    public static String generateTokenHerOkuApp2() {

        String url = "https://restful-booker.herokuapp.com/auth";
        Map<String, String> tokenBody = new HashMap<>();
        tokenBody.put("username", "admin");
        tokenBody.put("password", "password123");

        Response response = given().
                contentType(ContentType.JSON).
                body(tokenBody).
                when().post(url);

        return response.jsonPath().getString("token");
    }
}
