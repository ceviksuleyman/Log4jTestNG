package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.DataForTests;

import static io.restassured.RestAssured.given;

public class _20_DataDriven extends DataForTests {

    // npm install -g json server
    // json-server --watch db.json

    @DataProvider(name = "DataForPost")
    public Object[][] dataForPost() {  // 1.

        Object[][] data = new Object[3][2]; // rows 3 -> columns 2

        data[0][0] = "JavaCan";
        data[0][1] = "QA";

        data[1][0] = "JSCan";
        data[1][1] = "Developer";

        data[2][0] = "Alpert";
        data[2][1] = "Devops";

        return data;
    }

    @DataProvider(name = "DataForPost2")
    public Object[][] dataForPost2() {  // 2.


        return new Object[][]{
                {"Albert", "Tester"},
                {"Einstein", "Developer"},
                {"Thomas", "Devops"},
                {"Edison", "QA"}};
    }


    @Test(dataProvider = "DataForPost2")
    public void testPost(String name, String job) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("job", job);

        String baseUrl = "https://reqres.in/api";
        Response response = given().
                pathParams("users", "users").
                contentType(ContentType.JSON).
                body(jsonObject.toString()).
                when().
                post(baseUrl + "/{users}");

        response.then().
                statusCode(201).
                log().body();

        int id = response.jsonPath().getInt("id");
        System.out.println("Generated id = " + id);
    }

    @DataProvider(name = "DeleteData")
    public Object[][] dataForDelete() {

        return new Object[][]{{1}, {2}, {3}, {4}};
    }

    @Test(dataProvider = "DeleteData", dependsOnMethods = "testPost")
    public void testDelete(int id) {

        System.out.println("Deleted id = " + id);

        String baseUrl = "https://reqres.in/api";
        given().
                pathParams("users", "users", "id", id).
                contentType(ContentType.JSON).
                when().
                delete(baseUrl + "/{users}/{id}").
                then().
                statusCode(204).
                log().all();
    }

    @Parameters({"id"})
    @Test
    public void testDelete2(int id) {

        System.out.println("Value for id is : " + id);

        String baseUrl = "https://reqres.in/api";
        given().
                pathParams("users", "users", "id", id).
                contentType(ContentType.JSON).
                when().
                delete(baseUrl + "/{users}/{id}").
                then().
                statusCode(204).
                log().all();
    }
}
