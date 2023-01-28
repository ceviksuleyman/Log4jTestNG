package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.reqresPojo.Reqres;
import utilities.ConfigReader;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class _01_GetPojo {

    @Test
    public void test01() {

        Response response = given()
                .pathParams("myPath", "users")
                .queryParam("page", 2)
                .queryParam("id", 5)
                .when()
                .get(ConfigReader.getProperty("reqresApiBaseUrl") + "{myPath}");


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
}
