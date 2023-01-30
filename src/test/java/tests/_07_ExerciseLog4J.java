package tests;


import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(utilities.Listeners.class)
public class _07_ExerciseLog4J {

    private static Logger logger = LogManager.getLogger(_07_ExerciseLog4J.class.getName());

    @Test
    public void log4jTest() {

        /*logger.info("LOG INFO");
        logger.debug("LOG DEBUG");
        logger.error("LOG ERROR");
        logger.fatal("LOG FATAL");
        logger.warn("LOG WARN");*/
    }

    @Test(priority = 0)
    public void test01() {

        logger.info("get request url https://reqres.in/api/users?page=2&id=5");
        // https://reqres.in/api/users?page=2&id=5
        given().pathParams("myPath", "users")
                .queryParam("page", 2)
                .queryParam("id", 5)
                .when()
                .get(ConfigReader.getProperty("reqresApiBaseUrl") + "{myPath}")
                .prettyPrint();
    }

    @Test(priority = 1)
    public void parsingTestXMLResponse() {

        logger.info("get request url http://restapi.adequateshop.com/api/Traveler?page=1");
        // http://restapi.adequateshop.com/api/Traveler?page=1
        given()
                .when()
                .get(ConfigReader.getProperty("restApiBaseUrl") + "Traveler?page=1")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/xml; charset=utf-8")
                .body("TravelerinformationResponse.page", Matchers.equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].name", Matchers.equalTo("Developer"));

        Response response = given()
                .when()
                .get(ConfigReader.getProperty("restApiBaseUrl") + "Traveler?page=1");

        logger.info("Do Assertion getStatusCode");

        assertEquals(200, response.getStatusCode());
        assertEquals("application/xml; charset=utf-8", response.header("Content-Type"));


        logger.info("Do Assertion response.xmlPath()");
        // xmlPath
        String pageNO = response.xmlPath().get("TravelerinformationResponse.page").toString();
        assertEquals("1", pageNO);

        String travelerName = response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
        assertEquals("Developer", travelerName);


    }

    @Test(priority = 2)
    public void testXMLResponseBody() {

        Response response = given()
                .when()
                .get(ConfigReader.getProperty("restApiBaseUrl") + "Traveler?page=1");

        // 1.
        XmlPath xmlPath = response.xmlPath();
        xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation.email").forEach(System.out::println);


        // 2.
        xmlPath = new XmlPath(response.asString());


        // Verify total number of travelers
        List<String> travelers = xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation");
        assertEquals(10, travelers.size());


        // verify traveler name is present in response
        List<String> traveler_names = xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
        for (String travelerName : traveler_names) {

            logger.info(travelerName);
        }

        boolean status = false;
        for (String travelerName : traveler_names) {

            if (travelerName.contains("Developer")) {
                status = true;
                logger.info("travelerName.contains(\"Developer\")");
                break;
            }
        }
        assertTrue(status);
    }
}
