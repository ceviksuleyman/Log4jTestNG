package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class AutoExerciseBaseUrl {

    public static RequestSpecification spec;

    public static void autoExerciseSetup() {

        spec = new RequestSpecBuilder().
                setBaseUri("https://automationexercise.com/api/").
                build();
    }
}
