package tests;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class _06_XmlSchemaValidation {

    @Test
    public void xmlSchemaValidation() {

        String url = "http://restapi.adequateshop.com/api/Traveler";

        Response res = given()
                .when()
                .get(url);

        res.prettyPrint();

        // XmlSchema
        String travelerXmlSchemaFilePath = "src/test/java/testData/TravelerXmlSchema.xsd";

        res.then()
                .assertThat()
                .body(RestAssuredMatchers.matchesXsd(new File(travelerXmlSchemaFilePath)));
    }
}
