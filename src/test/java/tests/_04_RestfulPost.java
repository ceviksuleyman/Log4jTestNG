package tests;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class _04_RestfulPost {

    @Test
    public void test01() throws FileNotFoundException {


        File file = new File("src/test/java/TestData/RestfulPostBody.json");
        FileReader fileReader = new FileReader(file);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject expectedDataJSONObject = new JSONObject(jsonTokener);
        System.out.println("expectedDataJSONObject = " + expectedDataJSONObject);

        Response response = given().pathParam("1", "booking")
                .contentType(ContentType.JSON)
                .body(expectedDataJSONObject.toString())
                .when().post(ConfigReader.getProperty("restfulApiBaseUrl") + "{1}");

        response.prettyPrint();


        // JSONObject
        JSONObject actualJSONObject = new JSONObject(response.asString());
        System.out.println("ActualJSONObject = " + actualJSONObject);


        // Do Assertion
        // booking
        assertEquals(expectedDataJSONObject.getString("firstname"),
                actualJSONObject.getJSONObject("booking").getString("firstname"));
        assertEquals(expectedDataJSONObject.getString("lastname"),
                actualJSONObject.getJSONObject("booking").getString("lastname"));
        assertEquals(expectedDataJSONObject.getInt("totalprice"),
                actualJSONObject.getJSONObject("booking").getInt("totalprice"));
        assertEquals(expectedDataJSONObject.getBoolean("depositpaid"),
                actualJSONObject.getJSONObject("booking").getBoolean("depositpaid"));

        // bookingdates
        assertEquals(expectedDataJSONObject.getJSONObject("bookingdates").getString("checkin"),
                actualJSONObject.getJSONObject("booking").getJSONObject("bookingdates").getString("checkin"));

        assertEquals(expectedDataJSONObject.getJSONObject("bookingdates").getString("checkout"),
                actualJSONObject.getJSONObject("booking").getJSONObject("bookingdates").getString("checkout"));
    }
}
