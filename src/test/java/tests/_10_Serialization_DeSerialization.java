package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import pojos.JPHPojo;

import java.util.HashMap;

public class _10_Serialization_DeSerialization {

    /**
     * Body(json) ---> Request ----> Response(json)
     * 1) POJO
     * 2) HashMap
     * 3) JSON
     * 4) gson
     * Serialization   => Pojo ---> Json
     * De-serialization => json ---> Pojo
     */
    @Test
    public void convertPojoToJson() throws JsonProcessingException { // Serialization   => Pojo ---> Json

        // https://jsonplaceholder.typicode.com/todos

        // created java object using HashMap
        HashMap<Object, Object> testData = new HashMap<>();
        testData.put("userId", 64);
        testData.put("title", "Tidy your room");
        testData.put("completed", true);


        // created java object using pojo class
        JPHPojo jphPojo = new JPHPojo(201, 64, "Tidy your room", true);
        System.out.println("jphPojo = " + jphPojo);


        // convert java object --> json object
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jphPojo);
        System.out.println("jsonData = " + jsonData);

        /**
         * jsonData = {
         *   "id" : 201,
         *   "userId" : 64,
         *   "title" : "Tidy your room",
         *   "completed" : true
         * }
         */
    }

    @Test(priority = 1)
    public void convertJsonToPojo() throws JsonProcessingException { // De-serialization => json ---> Pojo

        String jsonData = "{\n" +
                "  \"id\" : 201,\n" +
                "  \"userId\" : 64,\n" +
                "  \"title\" : \"Tidy your room\",\n" +
                "  \"completed\" : true\n" +
                "}";
        System.out.println("jsonData = " + jsonData);

        // convert json object --> pojo object
        ObjectMapper objectMapper = new ObjectMapper();
        JPHPojo jphPojo = objectMapper.readValue(jsonData, JPHPojo.class);
        System.out.println("jphPojo = " + jphPojo);
    }
}
