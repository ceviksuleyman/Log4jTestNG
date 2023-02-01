package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.reqresPojo.Reqres;
import utilities.ConfigReader;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class _03_ReqresGet {

    @Test
    public void test01() {


        Response response = given()
                .pathParams("myPath", "users")
                .queryParam("page", 2)
                .queryParam("id", 5)
                .when()
                .get(ConfigReader.getProperty("reqresUrl") + "{myPath}");


        response.prettyPrint();


        Reqres actualData = response.as(Reqres.class);
        System.out.println(actualData);

        Reqres reqres = ObjectMapperUtils.convertJsonToJava(response.asString(), Reqres.class);
        System.out.println(reqres);


        System.out.println(reqres.getData().getFirst_name());
        System.out.println(reqres.getData().getLast_name());
        System.out.println(reqres.getData().getEmail());
        System.out.println(reqres.getData());
        System.out.println("Test01 The End\n");

    }

    @Test
    public void test02() {

        Response response = given()
                .pathParams("myPath", "users").queryParam("page", 2)
                .when()
                .get(ConfigReader.getProperty("reqresUrl") + "{myPath}");


        // JsonPath
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();
        System.out.println(jsonPath.getString("data[0].first_name"));

        response.then().body("data[0].first_name", Matchers.equalTo("Michael"));

        Assert.assertEquals("Michael", jsonPath.getString("data[0].first_name"));

        jsonPath.getList("data.findAll{it.email=='michael.lawson@reqres.in'}").stream().forEach(t -> System.out.println(t));
        jsonPath.getList("data.findAll{it.email=='michael.lawson@reqres.in'}.last_name").stream().forEach(t -> System.out.println(t));
        jsonPath.getList("data.findAll{it.first_name}.first_name").stream().forEach(t -> System.out.println(t));


        // JSONObject
        JSONObject jsonObject = new JSONObject(response.asString());
        //System.out.println("jsonObject = " + jsonObject);
        for (int i = 0; i < jsonObject.getJSONArray("data").length(); i++) {

            System.out.println(jsonObject.getJSONArray("data").getJSONObject(i));
            System.out.println(jsonObject.getJSONArray("data").getJSONObject(i).get("email").toString());
        }

        System.out.println(jsonObject.getJSONArray("data").getJSONObject(0).toString());


        boolean status = false;

        for (int i = 0; i < jsonObject.getJSONArray("data").length(); i++) {

            String email = jsonObject.getJSONArray("data").getJSONObject(i).get("email").toString();

            if (email.equals("michael.lawson@reqres.in")) {

                status = true;
                break;
            }
        }
        Assert.assertTrue(status);
    }
}
