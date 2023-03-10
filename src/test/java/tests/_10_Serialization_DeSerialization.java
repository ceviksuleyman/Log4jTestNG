package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import pojos.GoRestPojo;
import pojos.JPHPojo;
import utilities.ObjectMapperUtils;

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
        HashMap<Object, Object> dataMap = new HashMap<>();
        dataMap.put("id", 201);
        dataMap.put("userId", 64);
        dataMap.put("title", "Tidy your room");
        dataMap.put("completed", true);
        System.out.println("dataMap = " + dataMap);


        // created java object using pojo class
        JPHPojo jphPojo = new JPHPojo(201, 64, "Tidy your room", true);
        System.out.println("jphPojo = " + jphPojo);


        // convert java object --> json object
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jphPojo);
        System.out.println("jsonData = " + jsonData);


        String jsonData2 = ObjectMapperUtils.convertJavaObjectToJson(jphPojo);
        System.out.println("jsonData2 = " + jsonData2);

        String jsonData3 = ObjectMapperUtils.convertJavaObjectToJson(dataMap);
        System.out.println("jsonData3 = " + jsonData3);
        System.out.println("===================================================");
    }

    /**
     * Output
     * jsonData = {
     * "id" : 201,
     * "userId" : 64,
     * "title" : "Tidy your room",
     * "completed" : true
     * }
     */

    @Test(priority = 1)
    public void convertJsonToPojo() throws JsonProcessingException { // De-serialization => json ---> Pojo

        // 1
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


        JPHPojo jphPojo2 = ObjectMapperUtils.convertJsonToJava(jsonData, JPHPojo.class);
        System.out.println("jphPojo2 = " + jphPojo2);


        // 2 https://gorest.co.in/public/v1/users/201205
        String jsonData2 = "{\n" +
                "   \"meta\": null,\n" +
                "   \"data\": {\n" +
                "      \"id\": 201205,\n" +
                "      \"name\": \"Dhanpati Agarwal\",\n" +
                "      \"email\": \"agarwal_dhanpati@leffler.biz\",\n" +
                "      \"gender\": \"male\",\n" +
                "      \"status\": \"active\"\n" +
                "   }\n" +
                "}";
        System.out.println("jsonData2 = " + jsonData2);

        // convert json object --> pojo object
        GoRestPojo goRestPojo = objectMapper.readValue(jsonData2, GoRestPojo.class);
        System.out.println("goRestPojo = " + goRestPojo);


        GoRestPojo goRestPojo2 = ObjectMapperUtils.convertJsonToJava(jsonData2, GoRestPojo.class);
        System.out.println("goRestPojo2 = " + goRestPojo2);


        System.out.println("name : " + goRestPojo.getData().getName());
        System.out.println("email : " + goRestPojo.getData().getEmail());
    }
}
