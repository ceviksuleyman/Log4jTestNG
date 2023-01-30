package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class _11_Authentications {


    @Test
    public void basicAuthentication() {

        given()
                .auth().basic("postman", "password")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    @Test(priority = 2)
    public void digestAuthentication() {

        given()
                .auth().digest("postman", "password")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    @Test(priority = 3)
    public void preemptiveAuthentication() {

        given()
                .auth().preemptive().basic("postman", "password")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test(priority = 4)
    public void bearerTokenAuthentication() {

        String bearerToken = "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";

        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Ignore
    @Test(priority = 5)
    public void OAuth1Authentication() {

        // this is for OAuth1.0  Authentication
        given()
                .auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
                .when()
                .get("url")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void OAuth2Authentication() {

        // this is for OAuth2.0  Authentication
        given()
                .auth().oauth2("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")
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void apiKeyAuthentication() {

        // https://api.openweathermap.org/data/2.5/forecast/daily?q=Istabul&units=metric&cnt=7
        given().pathParams("myPath", "data/2.5/forecast/daily")
                .queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c") // appid is APIKey
                .queryParam("q", "Usak")
                .queryParam("units", "metric")
                .queryParam("cnt", "7")
                .when()
                .get("https://api.openweathermap.org/{myPath}")
                .then()
                .statusCode(200)
                .log().all();
    }
}