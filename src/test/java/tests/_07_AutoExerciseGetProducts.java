package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class _07_AutoExerciseGetProducts {

    private Logger logger = LogManager.getLogger(_07_AutoExerciseGetProducts.class.getName());

    @Test
    public void getProducts() throws IOException {

        Response response = given()
                .when()
                .get(ConfigReader.getProperty("autoExBaseUrl") + "productsList");

        JsonPath json = response.jsonPath();
        assertEquals(200, response.getStatusCode());
        assertEquals(200, json.getInt("responseCode"));

        List<String> namesList = json.getList("products.name");
        System.out.println(namesList);

        List<String> id = json.getList("products.findAll{it.id>=30}.brand");
        System.out.println(id);

        List<String> usertype = json.getList("products.category.usertype.findAll{it.usertype}.usertype");
        System.out.println(usertype.size());
        System.out.println(usertype);

        List<String> category = json.getList("products.category.findAll{it.category}.category");
        System.out.println(category.size());
        System.out.println(category);

        List<String> products = json.getList("products.name");
        System.out.println(products);

        System.out.println(json.getList("products.category.category"));
        System.out.println(json.getString("products[0].category.usertype.usertype"));

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/java/testData/Products.txt", false));
        List<String> productList = json.getList("products");
        for (int i = 0; i < productList.size(); i++) {

            Log.info(json.getString("products[" + i + "]\n")); // Log4j
            logger.info(json.getString("products[" + i + "]\n"));
            writer.append(json.getString("products[" + i + "]")).append(",\n");
            // System.out.println(json.getString("products[" + i + "]"));
        }
        writer.close();


        // JSONObject
        JSONObject jsonObject = new JSONObject(response.asString());
        for (int i = 0; i < jsonObject.getJSONArray("products").length(); i++) {

            System.out.println(jsonObject.getJSONArray("products").getJSONObject(i).get("brand"));
            System.out.println(jsonObject.getJSONArray("products").getJSONObject(i));
            System.out.println(jsonObject.getJSONArray("products").getJSONObject(i).getJSONObject("category").get("category"));
        }
    }
}
