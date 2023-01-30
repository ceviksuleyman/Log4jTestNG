package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static String generateToken() {

        String username = "Cevik";
        String password = "Cevikadmin64!";

        Map<String, Object> postBody = new HashMap<>();
        postBody.put("username", username);
        postBody.put("password", password);
        postBody.put("rememberMe", true);

        String endPoint = "https://www.medunna.com/api/authenticate";

        Response response = given().contentType(ContentType.JSON).body(postBody).when().post(endPoint);

        JsonPath json = response.jsonPath();

        return json.getString("id_token");
    }
}
