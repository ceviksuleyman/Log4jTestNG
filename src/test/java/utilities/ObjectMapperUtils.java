package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {

    private static final ObjectMapper mapper;

    static {

        mapper = new ObjectMapper();
    }

    public static <T> T convertJsonToJava(String json, Class<T> cls) {

        T javaResult = null;

        try {

            javaResult = mapper.readValue(json, cls);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return javaResult;
    }

    public static String convertJavaObjectToJson(Object obj) {

        String jsonResult = null;

        try {

            jsonResult = mapper.writeValueAsString(obj);

        } catch (IOException e) {

            e.printStackTrace();
        }
        return jsonResult;
    }
}
