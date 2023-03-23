package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;


public class HerOkuAppBase extends HerOkuAppAuth{

    protected RequestSpecification spec;

    @BeforeTest
    public void setUp() {
        spec = new RequestSpecBuilder()
                .addHeader("Cookie", "token=" + generateTokenHerOkuApp())
                .setContentType(ContentType.JSON)
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();
    }
}
