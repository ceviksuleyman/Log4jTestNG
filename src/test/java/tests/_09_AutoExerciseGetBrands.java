package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@Listeners(utilities.Listeners.class)
public class _09_AutoExerciseGetBrands {

    @Test
    public void getBrands() throws IOException {

        Response response = given()
                .when()
                .get(ConfigReader.getProperty("autoExBaseUrl") + "brandsList");


        JsonPath json = response.jsonPath();
        assertEquals(200, response.getStatusCode());
        assertEquals(200, json.getInt("responseCode"));

        json.prettyPrint();

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/java/testData/Brands.txt", false));
        List<String> brandsList = json.getList("brands");
        for (int i = 0; i < brandsList.size(); i++) {

            writer.append(json.getString("brands[" + i + "]")).append(",\n");
            System.out.println(json.getString("brands[" + i + "]"));
        }
        writer.close();


        // JSONObject
        JSONObject jsonObject = new JSONObject(response.asString());
        System.out.println(jsonObject);
        for (int i = 0; i < jsonObject.getJSONArray("brands").length(); i++) {

            System.out.println(jsonObject.getJSONArray("brands").getJSONObject(i));
            System.out.println(jsonObject.getJSONArray("brands").getJSONObject(i).get("brand").toString());
        }

        System.out.println(jsonObject.getJSONArray("brands").getJSONObject(0).toString());
    }
}
